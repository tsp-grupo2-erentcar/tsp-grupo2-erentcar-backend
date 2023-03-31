package com.api.rentcar.cars.api;

import com.api.rentcar.cars.domain.service.FeatureService;
import com.api.rentcar.cars.mapping.FeatureMapper;
import com.api.rentcar.cars.resource.CreateFeatureResource;
import com.api.rentcar.cars.resource.FeatureResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/features")
@CrossOrigin
public class FeatureController {
    private final FeatureService featureService;
    private final FeatureMapper mapper;

    public FeatureController(FeatureService featureService, FeatureMapper mapper) {
        this.featureService = featureService;
        this.mapper = mapper;
    }
    @GetMapping
    public Page<FeatureResource> getAllFeaturess(Pageable pageable){
        return mapper.modelListToPage(featureService.getAll(),pageable);
    }

    @GetMapping("{featureId}")
    public FeatureResource getFeatureById(@PathVariable Long featureId){
        return  mapper.toResource(featureService.getById(featureId));
    }

    @PostMapping("car/{carId}")
    public FeatureResource createFeature(@Valid @RequestBody CreateFeatureResource request,
                                         @PathVariable("carId")Long carId){
        return mapper.toResource(featureService.create(carId,mapper.toModel(request)));
    }

    @DeleteMapping("{featureId}")
    public ResponseEntity<?> deleteCar(@PathVariable Long featureId){
        return featureService.delete(featureId);
    }
}
