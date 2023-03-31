package com.api.rentcar.ratings.service;

import com.api.rentcar.ratings.domain.model.entity.RatingOwner;
import com.api.rentcar.ratings.domain.persistence.RatingOwnerRepository;
import com.api.rentcar.ratings.domain.service.RatingOwnerService;
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
public class RatingOwnerServiceImpl implements RatingOwnerService {
    private static final String ENTITY="RatingOwner";
    private final RatingOwnerRepository ratingOwnerRepository;
    private final ClientRepository clientRepository;
    private final OwnerRepository ownerRepository;
    private final Validator validator;

    public RatingOwnerServiceImpl(RatingOwnerRepository ratingOwnerRepository,
                                  ClientRepository clientRepository,
                                  OwnerRepository ownerRepository, Validator validator) {
        this.ratingOwnerRepository = ratingOwnerRepository;
        this.clientRepository = clientRepository;
        this.ownerRepository = ownerRepository;
        this.validator = validator;
    }

    @Override
    public List<RatingOwner> getAll() {
        return ratingOwnerRepository.findAll();
    }

    @Override
    public Page<RatingOwner> getAll(Pageable pageable) {
        return ratingOwnerRepository.findAll(pageable);
    }

    @Override
    public RatingOwner getById(Long ratingOwnerId) {
        return ratingOwnerRepository.findById(ratingOwnerId)
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,ratingOwnerId));
    }

    @Override
    public RatingOwner create(Long ownerId, Long clientId, RatingOwner ratingOwner) {
        Set<ConstraintViolation<RatingOwner>> violations=validator.validate(ratingOwner);
        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY,violations);
        }
        Client client=clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client", clientId));
        Owner owner=ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner", ownerId));
        ratingOwner.setClient(client);
        ratingOwner.setOwner(owner);

        RatingOwner ratingOwnerAux=ratingOwnerRepository.save(ratingOwner);
        Double promOwner=ratingOwnerRepository.getPromOwnerRating(ownerId);
        ownerRepository.findById(ownerId).map(aux->ownerRepository.save(aux.withRating(promOwner)))
                .orElseThrow(() -> new ResourceNotFoundException("Owner", ownerId));
        return ratingOwnerAux;
    }

    @Override
    public ResponseEntity<?> delete(Long ratingOwnerId) {
        return ratingOwnerRepository.findById(ratingOwnerId).map(ratingOwner -> {
            ratingOwnerRepository.delete(ratingOwner);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, ratingOwnerId));
    }
}
