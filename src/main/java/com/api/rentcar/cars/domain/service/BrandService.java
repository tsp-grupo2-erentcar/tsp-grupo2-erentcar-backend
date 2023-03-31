package com.api.rentcar.cars.domain.service;

import com.api.rentcar.cars.domain.model.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BrandService {
    List<Brand>getAll();
    Page<Brand>getAll(Pageable pageable);
    Brand getById(Long brandId);
    Brand create (Brand brand);
}
