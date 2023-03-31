package com.api.rentcar.users.api;

import com.api.rentcar.users.domain.service.OwnerService;
import com.api.rentcar.users.mapping.OwnerMapper;
import com.api.rentcar.users.resource.CreateOwnerResource;
import com.api.rentcar.users.resource.OwnerResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/owners")
@CrossOrigin
public class OwnerController {
    private final OwnerService ownerService;
    private final OwnerMapper mapper;

    public OwnerController(OwnerService ownerService, OwnerMapper mapper) {
        this.ownerService = ownerService;
        this.mapper = mapper;
    }
    @GetMapping
    public Page<OwnerResource> getAllOwners(Pageable pageable){
        return mapper.modelListToPage(ownerService.getAll(),pageable);
    }

    @GetMapping("{ownerId}")
    public OwnerResource getOwnerById(@PathVariable Long ownerId){
        return  mapper.toResource(ownerService.getById(ownerId));
    }

    @PostMapping
    public OwnerResource createOwner(@Valid @RequestBody CreateOwnerResource request){
        return mapper.toResource(ownerService.create(mapper.toModel(request)));
    }
    @PutMapping("{ownerId}/plan/{planId}")
    public OwnerResource updateOwnerPlan(@PathVariable("ownerId")Long ownerId,
                                         @PathVariable("planId")Long planId){
        return mapper.toResource(ownerService.updatePlan(ownerId,planId));
    }

    @GetMapping("email/{email}/password/{password}")
    public OwnerResource getOwnerById(@PathVariable("email")String email,
                                      @PathVariable("password")String password){
        return  mapper.toResource(ownerService.AuthOwner(email,password));
    }
}
