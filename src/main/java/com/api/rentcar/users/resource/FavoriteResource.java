package com.api.rentcar.users.resource;

import com.api.rentcar.cars.resource.CarResource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoriteResource {
    private Long id;
    private CarResource car;
}
