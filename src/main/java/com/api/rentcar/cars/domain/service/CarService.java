package com.api.rentcar.cars.domain.service;

import com.api.rentcar.cars.domain.model.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CarService {
    List<Car> getAll();
    Page<Car> getAll(Pageable pageable);
    Car getById(Long carId);
    Car create(Long brandId,Long ownerId,Car car);
    ResponseEntity<?> delete(Long carId);
    List<Car> getCarsNotRent();
    Car setState(Long carId);
}
