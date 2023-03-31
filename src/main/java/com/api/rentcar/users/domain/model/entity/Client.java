package com.api.rentcar.users.domain.model.entity;

import com.api.rentcar.ratings.domain.model.entity.RatingCar;
import com.api.rentcar.ratings.domain.model.entity.RatingClient;
import com.api.rentcar.ratings.domain.model.entity.RatingOwner;
import com.api.rentcar.rents.domain.model.entity.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "clients")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    private String image;

    private Double rating;

    @OneToMany(
            targetEntity = Favorite.class,
            mappedBy = "client",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Favorite> favorites;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(
            targetEntity = Reservation.class,
            mappedBy = "client",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Reservation> reservations;

    @OneToMany(
            targetEntity = RatingCar.class,
            mappedBy = "client",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<RatingCar> ratingCars;

    @OneToMany(
            targetEntity = RatingClient.class,
            mappedBy = "client",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<RatingClient> ratingClients;

    @OneToMany(
            targetEntity = RatingOwner.class,
            mappedBy = "client",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<RatingOwner> ratingOwners;
}
