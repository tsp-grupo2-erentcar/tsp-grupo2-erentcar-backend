package com.api.rentcar.cars.api;

import com.api.rentcar.cars.domain.service.CarService;
import com.api.rentcar.cars.mapping.CarMapper;
import com.api.rentcar.cars.resource.CarResource;
import com.api.rentcar.cars.resource.CreateCarResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/cars")
@CrossOrigin
public class CarController {
    private final CarService carService;
    private final CarMapper mapper;

    public CarController(CarService carService, CarMapper mapper) {
        this.carService = carService;
        this.mapper = mapper;
    }
    @GetMapping
    public Page<CarResource> getAllCars(Pageable pageable){
        return mapper.modelListToPage(carService.getAll(),pageable);
    }
    @GetMapping("notRents")
    public Page<CarResource> getAllCarsNotRent(Pageable pageable){
        return mapper.modelListToPage(carService.getCarsNotRent(),pageable);
    }

    @GetMapping("{carId}")
    public CarResource getCarById(@PathVariable Long carId){
        return  mapper.toResource(carService.getById(carId));
    }

    @PostMapping("owner/{ownerId}/brand/{brandId}")
    public CarResource createCar(@PathVariable("ownerId")Long ownerId,
                                 @PathVariable("brandId")Long brandId,
                                 @Valid @RequestBody CreateCarResource request){
        return mapper.toResource(carService.create(brandId,ownerId,mapper.toModel(request)));
    }
    @DeleteMapping("{carId}")
    public ResponseEntity<?> deleteCar(@PathVariable Long carId) {
        return carService.delete(carId);
    }
}
