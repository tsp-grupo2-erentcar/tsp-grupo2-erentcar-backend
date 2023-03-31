package com.api.rentcar.users.domain.service;

import com.api.rentcar.users.domain.model.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {
    List<Client> getAll();
    Page<Client> getAll(Pageable pageable);
    Client getById(Long clientId);
    Client create(Client client);
    Client AuthClient(String email, String password);
}
