package com.api.rentcar.cars.api;

import com.api.rentcar.cars.domain.service.BrandService;
import com.api.rentcar.cars.mapping.BrandMapper;
import com.api.rentcar.cars.resource.BrandResource;
import com.api.rentcar.cars.resource.CreateBrandResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/brands")
@CrossOrigin
public class BrandController {
    private final BrandService brandService;
    private final BrandMapper mapper;

    public BrandController(BrandService brandService, BrandMapper mapper) {
        this.brandService = brandService;
        this.mapper = mapper;
    }
    @GetMapping
    public Page<BrandResource> getAllBrands(Pageable pageable){
        return mapper.modelListToPage(brandService.getAll(),pageable);
    }

    @GetMapping("{brandId}")
    public BrandResource getBrandById(@PathVariable Long brandId){
        return  mapper.toResource(brandService.getById(brandId));
    }

    @PostMapping
    public BrandResource createBrand(@Valid @RequestBody CreateBrandResource request){
        return mapper.toResource(brandService.create(mapper.toModel(request)));
    }
}