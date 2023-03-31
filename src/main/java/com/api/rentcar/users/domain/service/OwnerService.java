package com.api.rentcar.users.domain.service;

import com.api.rentcar.users.domain.model.entity.Owner;
import com.api.rentcar.users.domain.model.entity.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OwnerService {
    List<Owner>getAll();
    Page<Owner>getAll(Pageable pageable);
    Owner getById(Long ownerId);
    Owner create(Owner owner);
    Owner updatePlan(Long ownerId, Long planId);
    Owner AuthOwner(String email,String password);
}
