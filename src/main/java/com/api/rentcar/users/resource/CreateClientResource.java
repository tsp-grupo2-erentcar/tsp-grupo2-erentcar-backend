package com.api.rentcar.users.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateClientResource {
    @NotNull
    @Size(max = 100)
    private String names;

    @NotNull
    @Size(max = 100)
    private String lastNames;

    @NotNull
    @Size(max = 100)
    private String email;

    @NotNull
    @Size(max = 20)
    private String password;

    private Long contactNumber;

    @NotNull
    private Long dni;

    @NotNull
    private String image;
}
