package com.api.rentcar.cars.domain.service;

import com.api.rentcar.cars.domain.model.entity.Feature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FeatureService {
    List<Feature> getAll();
    Page<Feature> getAll(Pageable pageable);
    Feature getById(Long featureId);
    Feature create(Long carId,Feature feature);
    ResponseEntity<?> delete(Long featureId);
}
