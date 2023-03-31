package com.api.rentcar.rents.domain.service;

import com.api.rentcar.rents.domain.model.entity.Rent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RentService {
    List<Rent> getAll();
    Page<Rent> getAll(Pageable pageable);
    Rent getById(Long rentId);
    Rent create(Long reservationId,Rent rent);
    List<Rent> getRentsByClientId(Long clientId);
    List<Rent> getRentsByOwnerId(Long ownerId);
    /*ResponseEntity<?> delete(Long rentId);*/
}
