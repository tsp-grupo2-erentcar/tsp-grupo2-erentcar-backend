package com.api.rentcar.users.domain.persistence;

import com.api.rentcar.users.domain.model.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Long> {
    @Query(value="select*from owners where email=?1 and password=?2", nativeQuery = true)
    Optional<Owner> findByEmailAndPassword(String email, String password);

}