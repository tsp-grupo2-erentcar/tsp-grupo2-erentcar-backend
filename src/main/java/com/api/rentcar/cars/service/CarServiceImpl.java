package com.api.rentcar.cars.service;

import com.api.rentcar.cars.domain.model.entity.Brand;
import com.api.rentcar.cars.domain.model.entity.Car;
import com.api.rentcar.cars.domain.persistence.BrandRepository;
import com.api.rentcar.cars.domain.persistence.CarRepository;
import com.api.rentcar.cars.domain.service.CarService;
import com.api.rentcar.shared.exception.ResourceNotFoundException;
import com.api.rentcar.shared.exception.ResourceValidationException;
import com.api.rentcar.users.domain.model.entity.Owner;
import com.api.rentcar.users.domain.persistence.OwnerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class CarServiceImpl implements CarService {
    private static final String ENTITY="Car";
    private final CarRepository carRepository;
    private final OwnerRepository ownerRepository;
    private final BrandRepository brandRepository;
    private final Validator validator;

    public CarServiceImpl(CarRepository carRepository, OwnerRepository ownerRepository, BrandRepository brandRepository, Validator validator) {
        this.carRepository = carRepository;
        this.ownerRepository = ownerRepository;
        this.brandRepository = brandRepository;
        this.validator = validator;
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public Page<Car> getAll(Pageable pageable) {
        return carRepository.findAll(pageable);
    }

    @Override
    public Car getById(Long carId) {
        return carRepository.findById(carId)
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,carId));
    }

    @Override
    public Car create(Long brandId,Long ownerId, Car car) {
        Set<ConstraintViolation<Car>> violations=validator.validate(car);
        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY,violations);
        }
        Brand brand=brandRepository.findById(brandId)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", brandId));
        car.setBrand(brand);

        Owner owner=ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner", ownerId));
        car.setOwner(owner);
        car.setRating(0.0);
        /*if(owner.getPlan().getName()=="Free" && owner.getCars().size()<2){
            return carRepository.save(car);
        }
        if(owner.getPlan().getName()=="Medium" && owner.getCars().size()<4){
            return carRepository.save(car);
        }
        if(owner.getPlan().getName()=="Pro" && owner.getCars().size()<6){
            return carRepository.save(car);
        }
        return null;*/
        return carRepository.save(car);
    }

    @Override
    public ResponseEntity<?> delete(Long carId) {
        return carRepository.findById(carId).map(car -> {
            carRepository.delete(car);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, carId));
    }

    @Override
    public List<Car> getCarsNotRent() {
        return carRepository.getCarsNotRents();
    }

    @Override
    public Car setState(Long carId) {
        Car car=carRepository.findById(carId)
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,carId));
        carRepository.save(car.withState(1));
        car.setState(1);
        return car;
    }
}
