package com.api.rentcar.users.service;

import com.api.rentcar.shared.exception.ResourceNotFoundException;
import com.api.rentcar.shared.exception.ResourceValidationException;
import com.api.rentcar.users.domain.model.entity.Client;
import com.api.rentcar.users.domain.persistence.ClientRepository;
import com.api.rentcar.users.domain.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {
    private static final String ENTITY="Client";
    private final ClientRepository clientRepository;
    private final Validator validator;

    public ClientServiceImpl(ClientRepository clientRepositoryRepository, Validator validator) {
        this.clientRepository = clientRepositoryRepository;
        this.validator = validator;
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Page<Client> getAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public Client getById(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,clientId));
    }

    @Override
    public Client create(Client client) {
        Set<ConstraintViolation<Client>> violations=validator.validate(client);
        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY,violations);
        }
        client.setRating(0.0);
        return clientRepository.save(client);
    }

    @Override
    public Client AuthClient(String email, String password) {
        return clientRepository.findByEmailAndPassword(email,password)
                .orElseThrow(()->new ResourceNotFoundException("Client not Found"));
    }
}
