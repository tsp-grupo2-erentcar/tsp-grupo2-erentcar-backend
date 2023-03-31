package com.api.rentcar.cars.resource;

import com.api.rentcar.cars.domain.model.entity.Brand;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CarResource {

    private Long id;

    private String model;

    private String plateNumber;

    private Double dayCost;

    private Double mileage;

    private String image;

    private String type;

    private String description;

    private int state;

    private Double rating;

    private BrandResource brand;
    /*private Brand brand;*/
    private List<FeatureResource>features;
}
