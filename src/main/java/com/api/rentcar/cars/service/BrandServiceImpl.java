package com.api.rentcar.cars.service;

import com.api.rentcar.cars.domain.model.entity.Brand;
import com.api.rentcar.cars.domain.persistence.BrandRepository;
import com.api.rentcar.cars.domain.service.BrandService;
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
public class BrandServiceImpl implements BrandService {
    private static final String ENTITY="Brand";
    private final BrandRepository brandRepository;
    private final Validator validator;

    public BrandServiceImpl(BrandRepository brandRepository, Validator validator) {
        this.brandRepository = brandRepository;
        this.validator = validator;
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public Page<Brand> getAll(Pageable pageable) {
        return brandRepository.findAll(pageable);
    }

    @Override
    public Brand getById(Long brandId) {
        return brandRepository.findById(brandId)
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,brandId));
    }

    @Override
    public Brand create(Brand brand) {
        Set<ConstraintViolation<Brand>> violations=validator.validate(brand);
        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY,violations);
        }
        return brandRepository.save(brand);
    }
}