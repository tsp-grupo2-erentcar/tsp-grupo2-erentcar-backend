package com.api.rentcar.users.resource;

import com.api.rentcar.cars.resource.CarResource;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OwnerResource {
    private Long id;
    private String names;
    private String lastNames;
    private String email;
    private String password;
    private Long contactNumber;
    private Long dni;
    private String image;
    private Double rating;
    private List<CarResource> cars;
    private PlanResource plan;
}
