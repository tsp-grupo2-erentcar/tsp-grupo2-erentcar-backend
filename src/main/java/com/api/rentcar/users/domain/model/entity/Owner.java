package com.api.rentcar.users.domain.model.entity;

import com.api.rentcar.cars.domain.model.entity.Car;
import com.api.rentcar.ratings.domain.model.entity.RatingClient;
import com.api.rentcar.ratings.domain.model.entity.RatingOwner;
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
@Table(name = "owners")
public class Owner implements Serializable {
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

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(
            targetEntity = Car.class,
            mappedBy = "owner",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Car> cars;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "plan_id",
            referencedColumnName = "id",
            nullable = false
    )
    @JsonIgnore
    private Plan plan;

    @OneToMany(
            targetEntity = RatingClient.class,
            mappedBy = "owner",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<RatingClient> ratingClients;

    @OneToMany(
            targetEntity = RatingOwner.class,
            mappedBy = "owner",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<RatingOwner> ratingOwners;
}
