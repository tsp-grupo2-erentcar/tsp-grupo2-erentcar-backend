package com.api.rentcar.cars.service;

import com.api.rentcar.cars.domain.model.entity.Car;
import com.api.rentcar.cars.domain.model.entity.Feature;
import com.api.rentcar.cars.domain.persistence.CarRepository;
import com.api.rentcar.cars.domain.persistence.FeatureRepository;
import com.api.rentcar.cars.domain.service.FeatureService;
import com.api.rentcar.shared.exception.ResourceNotFoundException;
import com.api.rentcar.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class FeatureServiceImpl implements FeatureService {
    private static final String ENTITY="Feature";
    private final Validator validator;
    private final FeatureRepository featureRepository;
    private final CarRepository carRepository;

    public FeatureServiceImpl(Validator validator, FeatureRepository featureRepository, CarRepository carRepository) {
        this.validator = validator;
        this.featureRepository = featureRepository;
        this.carRepository = carRepository;
    }

    @Override
    public List<Feature> getAll() {
        return featureRepository.findAll();
    }

    @Override
    public Page<Feature> getAll(Pageable pageable) {
        return featureRepository.findAll(pageable);
    }

    @Override
    public Feature getById(Long featureId) {
        return featureRepository.findById(featureId)
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,featureId));
    }

    @Override
    public Feature create(Long carId, Feature feature) {
        Set<ConstraintViolation<Feature>> violations=validator.validate(feature);
        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY,violations);
        }
        Car car=carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car", carId));
        feature.setCar(car);
        return featureRepository.save(feature);
    }

    @Override
    public ResponseEntity<?> delete(Long featureId) {
        return featureRepository.findById(featureId).map(feature -> {
            featureRepository.delete(feature);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, featureId));
    }
}
