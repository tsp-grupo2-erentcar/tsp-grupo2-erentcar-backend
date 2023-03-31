package com.api.rentcar.rents.resource;

import com.api.rentcar.cars.resource.CarResource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class CreateReservationResource {
    @NotNull
    private Date reserveDate;
    @NotNull
    private Date returnDate;
    @NotNull
    private int status;
}
