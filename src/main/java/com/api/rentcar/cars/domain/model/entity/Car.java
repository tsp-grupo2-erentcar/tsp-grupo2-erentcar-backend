package com.api.rentcar.cars.domain.model.entity;

import com.api.rentcar.ratings.domain.model.entity.RatingCar;
import com.api.rentcar.rents.domain.model.entity.Reservation;
import com.api.rentcar.users.domain.model.entity.Favorite;
import com.api.rentcar.users.domain.model.entity.Owner;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "cars")
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @NotNull
    private Integer state;

    private Double rating;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "owner_id",
            referencedColumnName = "id",
            nullable = false
    )
    @JsonIgnore
    private Owner owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "brand_id",
            referencedColumnName = "id",
            nullable = false
    )
    @JsonIgnore
    private Brand brand;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(
            targetEntity = Feature.class,
            mappedBy = "car",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Feature> features;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(
            targetEntity = Favorite.class,
            mappedBy = "car",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Favorite> favorites;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(
            targetEntity = Reservation.class,
            mappedBy = "car",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Reservation> reservations;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(
            targetEntity = RatingCar.class,
            mappedBy = "car",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<RatingCar> ratingCars;
}