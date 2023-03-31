package com.api.rentcar.rents.service;

import com.api.rentcar.cars.domain.model.entity.Car;
import com.api.rentcar.cars.domain.persistence.CarRepository;
import com.api.rentcar.rents.domain.model.entity.Rent;
import com.api.rentcar.rents.domain.model.entity.Reservation;
import com.api.rentcar.rents.domain.persistence.RentRepository;
import com.api.rentcar.rents.domain.persistence.ReservationRepository;
import com.api.rentcar.rents.domain.service.RentService;
import com.api.rentcar.shared.exception.ResourceNotFoundException;
import com.api.rentcar.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class RentServiceImpl implements RentService {
    private static final String ENTITY="Rent";
    private final RentRepository rentRepository;
    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final Validator validator;

    public RentServiceImpl(RentRepository rentRepository, ReservationRepository reservationRepository, CarRepository carRepository, Validator validator) {
        this.rentRepository = rentRepository;
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
        this.validator = validator;
    }

    @Override
    public List<Rent> getAll() {
        return rentRepository.findAll();
    }

    @Override
    public Page<Rent> getAll(Pageable pageable) {
        return rentRepository.findAll(pageable);
    }

    @Override
    public Rent getById(Long rentId) {
        return rentRepository.findById(rentId)
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,rentId));
    }

    @Override
    public Rent create(Long reservationId, Rent rent) {
        Set<ConstraintViolation<Rent>> violations=validator.validate(rent);
        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY,violations);
        }
        Reservation reservation=reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", reservationId));
        rent.setReservation(reservation);
        Car car=reservation.getCar();
        if(car.getState()==1){
            return null;
        }
        rent.setStatus(0);
        carRepository.findById(car.getId()).map(carAux->carRepository.save(carAux.withState(1)));
        return rentRepository.save(rent);
    }

    @Override
    public List<Rent> getRentsByClientId(Long clientId) {
        return rentRepository.getRentsByClientId(clientId);
    }

    @Override
    public List<Rent> getRentsByOwnerId(Long ownerId) {
        return rentRepository.getRentsByClientId(ownerId);
    }
}
