package com.acme.webserviceserentcar.client.domain.persistence;

import com.acme.webserviceserentcar.client.domain.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {


    Optional<Client> findByUserId(Long userId);
}
