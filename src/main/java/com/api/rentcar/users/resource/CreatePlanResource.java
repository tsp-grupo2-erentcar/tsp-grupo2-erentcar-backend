package com.api.rentcar.users.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreatePlanResource {
    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    private Double price;
}
