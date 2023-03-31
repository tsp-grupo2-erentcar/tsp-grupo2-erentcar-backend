package com.api.rentcar.users.service;

import com.api.rentcar.shared.exception.ResourceNotFoundException;
import com.api.rentcar.users.domain.model.entity.Owner;
import com.api.rentcar.users.domain.model.entity.Plan;
import com.api.rentcar.users.domain.persistence.OwnerRepository;
import com.api.rentcar.users.domain.persistence.PlanRepository;
import com.api.rentcar.users.domain.service.OwnerService;
import com.api.rentcar.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class OwnerServiceImpl implements OwnerService {
    private static final String ENTITY="Owner";
    private final OwnerRepository ownerRepository;
    private final PlanRepository planRepository;
    private final Validator validator;

    public OwnerServiceImpl(OwnerRepository ownerRepository, PlanRepository planRepository,
                            Validator validator) {
        this.ownerRepository = ownerRepository;
        this.planRepository = planRepository;
        this.validator = validator;
    }

    @Override
    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }

    @Override
    public Page<Owner> getAll(Pageable pageable) {
        return ownerRepository.findAll(pageable);
    }

    @Override
    public Owner getById(Long ownerId) {
        return ownerRepository.findById(ownerId)
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,ownerId));
    }

    @Override
    public Owner create(Owner owner) {
        Set<ConstraintViolation<Owner>>violations=validator.validate(owner);
        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY,violations);
        }
        Plan plan=planRepository.findById(1L)
                .orElseThrow(()->new ResourceNotFoundException("Plan", 1L));
        owner.setPlan(plan);
        owner.setRating(0.0);
        return ownerRepository.save(owner);
    }

    @Override
    public Owner updatePlan(Long ownerId, Long planId) {
        Plan plan=planRepository.findById(planId)
                .orElseThrow(()->new ResourceNotFoundException("Plan", planId));
        Owner owner=ownerRepository.findById(ownerId).map(planAux->
                        ownerRepository.save(planAux.withPlan(plan)))
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,ownerId));
        /*owner.setPlan(plan);*/
        return owner;
    }

    @Override
    public Owner AuthOwner(String email, String password) {
        return ownerRepository.findByEmailAndPassword(email,password)
                .orElseThrow(()->new ResourceNotFoundException("Owner Not Found"));
    }
}
