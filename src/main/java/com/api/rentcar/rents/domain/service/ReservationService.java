package com.api.rentcar.rents.domain.service;

import com.api.rentcar.rents.domain.model.entity.Rent;
import com.api.rentcar.rents.domain.model.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    List<Reservation> getAll();
    Page<Reservation> getAll(Pageable pageable);
    Reservation getById(Long reservationId);
    Reservation create(Long clientId,Long carId,Reservation reservation);
    ResponseEntity<?> delete(Long reservationId);
    Reservation updateStatus(Long reservationId, int status);
    Reservation cancel(Long reservationId);
    Reservation cancelPay(Long reservationId);
    Rent pay(Long reservationId, Rent rent);
    List<Reservation> getReservationsByOwner(Long ownerId);
    List<Reservation> getReservationsByClient(Long clientId);
}
