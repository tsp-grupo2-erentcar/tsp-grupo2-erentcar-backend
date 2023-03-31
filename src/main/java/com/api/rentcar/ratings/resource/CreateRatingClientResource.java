package com.api.rentcar.ratings.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateRatingClientResource {
    @NotNull
    @Size(max = 255)
    private String comment;

    @NotNull
    private int rating;
}
