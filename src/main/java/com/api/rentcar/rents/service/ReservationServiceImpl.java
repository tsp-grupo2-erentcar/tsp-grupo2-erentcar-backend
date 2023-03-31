package com.api.rentcar.rents.service;

import com.api.rentcar.cars.domain.model.entity.Car;
import com.api.rentcar.cars.domain.persistence.CarRepository;
import com.api.rentcar.rents.domain.model.entity.Rent;
import com.api.rentcar.rents.domain.model.entity.Reservation;
import com.api.rentcar.rents.domain.persistence.RentRepository;
import com.api.rentcar.rents.domain.persistence.ReservationRepository;
import com.api.rentcar.rents.domain.service.ReservationService;
import com.api.rentcar.shared.exception.ResourceNotFoundException;
import com.api.rentcar.shared.exception.ResourceValidationException;
import com.api.rentcar.users.domain.model.entity.Client;
import com.api.rentcar.users.domain.persistence.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class ReservationServiceImpl implements ReservationService {
    private static final String ENTITY="RESERVATION";
    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final CarRepository carRepository;
    private final RentRepository rentRepository;
    private final Validator validator;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  ClientRepository clientRepository,
                                  CarRepository carRepository, RentRepository rentRepository, Validator validator) {
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
        this.carRepository = carRepository;
        this.rentRepository = rentRepository;
        this.validator = validator;
    }

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Page<Reservation> getAll(Pageable pageable) {
        return reservationRepository.findAll(pageable);
    }

    @Override
    public Reservation getById(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,reservationId));
    }

    @Override
    public Reservation create(Long clientId, Long carId, Reservation reservation) {
        Set<ConstraintViolation<Reservation>> violations=validator.validate(reservation);
        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY,violations);
        }
        Client client=clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client", clientId));
        Car car=carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car", carId));

        if(car.getState()!=0){
            return null;
        }
        Double costPerDay=car.getDayCost();
        Long duration=reservation.getReturnDate().getTime()-reservation.getReserveDate().getTime();
        Long daysDifference= TimeUnit.MILLISECONDS.toDays(duration);

        reservation.setClient(client);
        reservation.setCar(car);
        reservation.setMount(costPerDay*daysDifference);

        /*if(reservation.getClient().getRating()>2.0 ||
                (reservation.getClient().getRating()<=2.0 && reservation.getClient().getReservations().size()<=3)){
            carRepository.findById(car.getId()).map(carAux->carRepository.save(carAux.withState(2)));
            return reservationRepository.save(reservation);
        }*/
        if(reservation.getClient().getRating()>2.0 ||
                (reservation.getClient().getRating()<=2.0 && rentRepository.getRentsByClientId(clientId).size()<=3)) {
            carRepository.findById(car.getId()).map(carAux -> carRepository.save(carAux.withState(2)));
            return reservationRepository.save(reservation);
        }
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long reservationId) {
        return reservationRepository.findById(reservationId).map(reservation -> {
            reservationRepository.delete(reservation);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, reservationId));
    }

    @Override
    public Reservation updateStatus(Long reservationId, int status) {
        Reservation reservation=reservationRepository.findById(reservationId).map(reservationAux->
                reservationRepository.save(reservationAux.withStatus(status))).
                orElseThrow(()->new ResourceNotFoundException(ENTITY,reservationId));
        reservation.setStatus(status);
        return reservation;
    }

    @Override
    public Reservation cancel(Long reservationId) {
        Reservation reservation=reservationRepository.findById(reservationId).map(reservationAux->
                        reservationRepository.save(reservationAux.withStatus(2))).
                orElseThrow(()->new ResourceNotFoundException(ENTITY,reservationId));
        reservation.setStatus(2);
        Car car=reservation.getCar();
        carRepository.findById(car.getId()).map(carAux->carRepository.save(carAux.withState(0)));
        return reservation;
    }

    @Override
    public Reservation cancelPay(Long reservationId) {
        Reservation reservation=reservationRepository.findById(reservationId).map(reservationAux->
                        reservationRepository.save(reservationAux.withStatus(3))).
                orElseThrow(()->new ResourceNotFoundException(ENTITY,reservationId));
        reservation.setStatus(2);
        Car car=reservation.getCar();
        carRepository.findById(car.getId()).map(carAux->carRepository.save(carAux.withState(0)));
        return reservation;
    }

    @Override
    public Rent pay(Long reservationId, Rent rent) {
        Reservation reservation=reservationRepository.findById(reservationId)
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,reservationId));
        Car car=reservation.getCar();
        carRepository.findById(car.getId()).map(carAux->carRepository.save(carAux.withState(1)));
        rent.setReservation(reservation);
        return rentRepository.save(rent);
    }

    @Override
    public List<Reservation> getReservationsByOwner(Long ownerId) {
        return reservationRepository.getReservationsByOwner(ownerId);
    }

    @Override
    public List<Reservation> getReservationsByClient(Long clientId) {
        return reservationRepository.getReservationsByClient(clientId);
    }
}
