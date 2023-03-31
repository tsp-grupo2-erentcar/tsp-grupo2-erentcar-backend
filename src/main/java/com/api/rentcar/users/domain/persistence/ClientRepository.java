package com.api.rentcar.users.domain.persistence;

import com.api.rentcar.users.domain.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    @Query(value="select*from clients where email=?1 and password=?2", nativeQuery = true)
    Optional<Client> findByEmailAndPassword(String email,String password);
}
