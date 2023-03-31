package com.api.rentcar.ratings.service;

import com.api.rentcar.cars.domain.model.entity.Car;
import com.api.rentcar.cars.domain.persistence.CarRepository;
import com.api.rentcar.ratings.domain.model.entity.RatingCar;
import com.api.rentcar.ratings.domain.persistence.RatingCarRepository;
import com.api.rentcar.ratings.domain.service.RatingCarService;
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

@Service
public class RatingCarServiceImpl implements RatingCarService {
    private static final String ENTITY="RatingCar";
    private final RatingCarRepository ratingCarRepository;
    private final ClientRepository clientRepository;
    private final CarRepository carRepository;
    private final Validator validator;

    public RatingCarServiceImpl(RatingCarRepository ratingCarRepository,
                                ClientRepository clientRepository,
                                CarRepository carRepository, Validator validator) {
        this.ratingCarRepository = ratingCarRepository;
        this.clientRepository = clientRepository;
        this.carRepository = carRepository;
        this.validator = validator;
    }

    @Override
    public List<RatingCar> getAll() {
        return ratingCarRepository.findAll();
    }

    @Override
    public Page<RatingCar> getAll(Pageable pageable) {
        return ratingCarRepository.findAll(pageable);
    }

    @Override
    public RatingCar getById(Long ratingCarId) {
        return ratingCarRepository.findById(ratingCarId)
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,ratingCarId));
    }

    @Override
    public RatingCar create(Long clientId, Long carId, RatingCar ratingCar) {
        Set<ConstraintViolation<RatingCar>> violations=validator.validate(ratingCar);
        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY,violations);
        }
        Client client=clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client", clientId));
        Car car=carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car", carId));
        ratingCar.setClient(client);
        ratingCar.setCar(car);

        RatingCar ratingCarAux=ratingCarRepository.save(ratingCar);
        Double promCar=ratingCarRepository.getPromCarRating(carId);
        carRepository.findById(carId).map(aux->carRepository.save(aux.withRating(promCar)))
                .orElseThrow(() -> new ResourceNotFoundException("Car", carId));

        return ratingCarAux;
    }

    @Override
    public ResponseEntity<?> delete(Long ratingCarId) {
        return ratingCarRepository.findById(ratingCarId).map(ratingCar -> {
            ratingCarRepository.delete(ratingCar);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, ratingCarId));
    }
}