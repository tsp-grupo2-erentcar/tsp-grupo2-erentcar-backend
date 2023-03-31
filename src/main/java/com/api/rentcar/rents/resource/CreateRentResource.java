package com.api.rentcar.rents.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class CreateRentResource {
    @NotNull
    private Date payDate;
}
