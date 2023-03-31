package com.api.rentcar.users.domain.service;

import com.api.rentcar.users.domain.model.entity.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface PlanService {
    List<Plan> getAll();
    Page<Plan> getAll(Pageable pageable);
    Plan getById(Long planId);
    Plan create(Plan plan);
    ResponseEntity<?> delete(Long planId);
}
