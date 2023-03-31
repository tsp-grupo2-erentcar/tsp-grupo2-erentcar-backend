package com.api.rentcar.cars.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeatureResource {
    private Long id;

    private int type;

    private String description;
}
