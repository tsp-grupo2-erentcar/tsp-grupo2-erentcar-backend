package com.api.rentcar.ratings.service;

import com.api.rentcar.ratings.domain.model.entity.RatingClient;
import com.api.rentcar.ratings.domain.persistence.RatingClientRepository;
import com.api.rentcar.ratings.domain.service.RatingClientService;
import com.api.rentcar.shared.exception.ResourceNotFoundException;
import com.api.rentcar.shared.exception.ResourceValidationException;
import com.api.rentcar.users.domain.model.entity.Client;
import com.api.rentcar.users.domain.model.entity.Owner;
import com.api.rentcar.users.domain.persistence.ClientRepository;
import com.api.rentcar.users.domain.persistence.OwnerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class RatingClientServiceImpl implements RatingClientService {
    private static final String ENTITY="RatingClient";
    private final RatingClientRepository ratingClientRepository;
    private final ClientRepository clientRepository;
    private final OwnerRepository ownerRepository;
    private final Validator validator;

    public RatingClientServiceImpl(RatingClientRepository ratingClientRepository,
                                   ClientRepository clientRepository,
                                   OwnerRepository carRepository, Validator validator) {
        this.ratingClientRepository = ratingClientRepository;
        this.clientRepository = clientRepository;
        this.ownerRepository = carRepository;
        this.validator = validator;
    }

    @Override
    public List<RatingClient> getAll() {
        return ratingClientRepository.findAll();
    }

    @Override
    public Page<RatingClient> getAll(Pageable pageable) {
        return ratingClientRepository.findAll(pageable);
    }

    @Override
    public RatingClient getById(Long ratingClientId) {
        return ratingClientRepository.findById(ratingClientId)
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,ratingClientId));
    }

    @Override
    public RatingClient create(Long clientId, Long ownerId, RatingClient ratingClient) {
        Set<ConstraintViolation<RatingClient>> violations=validator.validate(ratingClient);
        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY,violations);
        }
        Client client=clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client", clientId));
        Owner owner=ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner", ownerId));
        ratingClient.setClient(client);
        ratingClient.setOwner(owner);
        RatingClient aux=ratingClientRepository.save(ratingClient);

        Double promClient=ratingClientRepository.getPromClientRating(clientId);
        clientRepository.findById(clientId).map(ratingClientAux->clientRepository
                .save(ratingClientAux.withRating(promClient)));

        return aux;
    }

    @Override
    public ResponseEntity<?> delete(Long ratingClientId) {
        return ratingClientRepository.findById(ratingClientId).map(ratingClient -> {
            ratingClientRepository.delete(ratingClient);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, ratingClientId));
    }
}
