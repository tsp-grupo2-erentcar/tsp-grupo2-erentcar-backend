package com.api.rentcar.users.service;

import com.api.rentcar.shared.exception.ResourceNotFoundException;
import com.api.rentcar.shared.exception.ResourceValidationException;
import com.api.rentcar.users.domain.model.entity.Plan;
import com.api.rentcar.users.domain.persistence.PlanRepository;
import com.api.rentcar.users.domain.service.PlanService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class PlanServiceImpl implements PlanService {
    private static final String ENTITY="Plan";
    private final PlanRepository planRepository;
    private final Validator validator;

    public PlanServiceImpl(PlanRepository planRepository, Validator validator) {
        this.planRepository = planRepository;
        this.validator = validator;
    }

    @Override
    public List<Plan> getAll() {
        return planRepository.findAll();
    }

    @Override
    public Page<Plan> getAll(Pageable pageable) {
        return planRepository.findAll(pageable);
    }

    @Override
    public Plan getById(Long planId) {
        return planRepository.findById( planId)
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,planId));
    }

    @Override
    public Plan create(Plan plan) {
        Set<ConstraintViolation<Plan>> violations=validator.validate(plan);
        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY,violations);
        }
        return planRepository.save(plan);
    }

    @Override
    public ResponseEntity<?> delete(Long planId) {
        return planRepository.findById(planId).map(plan -> {
            planRepository.delete(plan);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, planId));
    }
}
