package com.api.rentcar.cars.resource;

import com.api.rentcar.cars.domain.model.entity.Brand;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateCarResource {
    @NotNull
    @Size(max = 100)
    private String model;

    @NotNull
    @Size(max = 12)
    private String plateNumber;

    @NotNull
    private Double dayCost;

    private Double mileage;

    @NotNull
    private String image;

    @NotNull
    @Size(max = 50)
    private String type;

    @NotNull
    private String description;


    private Integer state;

}
