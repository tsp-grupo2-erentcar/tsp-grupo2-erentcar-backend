package com.api.rentcar.ratings.domain.service;

import com.api.rentcar.ratings.domain.model.entity.RatingCar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RatingCarService {
    List<RatingCar> getAll();
    Page<RatingCar> getAll(Pageable pageable);
    RatingCar getById(Long ratingCarId);
    RatingCar create(Long clientId,Long carId,RatingCar ratingCar);
    ResponseEntity<?> delete(Long ratingCarId);
}
