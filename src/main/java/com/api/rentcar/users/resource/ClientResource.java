package com.api.rentcar.users.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientResource {
    private Long id;
    private String names;
    private String lastNames;
    private String email;
    private String password;
    private Long contactNumber;
    private Long dni;
    private String image;
    private Double rating;
    private List<FavoriteResource>favorites;
}
