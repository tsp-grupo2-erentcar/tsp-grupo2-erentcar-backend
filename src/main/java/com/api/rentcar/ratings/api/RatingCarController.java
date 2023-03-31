package com.api.rentcar.ratings.api;

import com.api.rentcar.ratings.domain.service.RatingCarService;
import com.api.rentcar.ratings.mapping.RatingCarMapper;
import com.api.rentcar.ratings.resource.CreateRatingCarResource;
import com.api.rentcar.ratings.resource.RatingCarResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/ratingCars")
@CrossOrigin
public class RatingCarController {
    private final RatingCarService ratingCarService;
    private final RatingCarMapper mapper;

    public RatingCarController(RatingCarService ratingCarService, RatingCarMapper mapper) {
        this.ratingCarService = ratingCarService;
        this.mapper = mapper;
    }
    @GetMapping
    public Page<RatingCarResource> getAllRatingCar(Pageable pageable){
        return mapper.modelListToPage(ratingCarService.getAll(),pageable);
    }

    @GetMapping("{ratingCarId}")
    public RatingCarResource getRatingCarById(@PathVariable Long ratingCarId){
        return  mapper.toResource(ratingCarService.getById(ratingCarId));
    }
    @PostMapping("client/{clientId}/car/{carId}")
    public RatingCarResource createRatingCar(@PathVariable("clientId")Long clientId,
                                             @PathVariable("carId")Long carId,
                                     @Valid @RequestBody CreateRatingCarResource request){
        return mapper.toResource(ratingCarService.create(clientId,carId, mapper.toModel(request)));
    }
    @DeleteMapping("{ratingCarId}")
    public ResponseEntity<?> deleteRatingCar(@PathVariable Long ratingCarId) {
        return ratingCarService.delete(ratingCarId);
    }
}
