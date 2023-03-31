package com.api.rentcar.users.api;

import com.api.rentcar.users.domain.service.PlanService;
import com.api.rentcar.users.mapping.PlanMapper;
import com.api.rentcar.users.resource.CreatePlanResource;
import com.api.rentcar.users.resource.PlanResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/plans")
@CrossOrigin
public class PlanController {
    private final PlanService planService;
    private final PlanMapper mapper;

    public PlanController(PlanService planService, PlanMapper mapper) {
        this.planService = planService;
        this.mapper = mapper;
    }
    @GetMapping
    public Page<PlanResource> getAllPlans(Pageable pageable){
        return mapper.modelListToPage(planService.getAll(),pageable);
    }

    @GetMapping("{planId}")
    public PlanResource getPlanById(@PathVariable Long planId){
        return  mapper.toResource(planService.getById(planId));
    }

    @PostMapping
    public PlanResource createPlan(@Valid @RequestBody CreatePlanResource request){
        return mapper.toResource(planService.create(mapper.toModel(request)));
    }
    @DeleteMapping("{planId}")
    public ResponseEntity<?> deletePlan(@PathVariable Long planId) {
        return planService.delete(planId);
    }
}
