package com.api.rentcar.cars.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateFeatureResource {
    @NotNull
    private int type;

    @NotNull
    @Size(max = 100)
    private String description;
}
