package com.api.rentcar.cars.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateBrandResource {
    @NotNull
    private String name;
    @NotNull
    private String image;
}