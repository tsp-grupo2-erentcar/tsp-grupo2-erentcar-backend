package com.api.rentcar.ratings.domain.model.entity;

import com.api.rentcar.users.domain.model.entity.Client;
import com.api.rentcar.users.domain.model.entity.Owner;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "ratingClients")
public class RatingClient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    private String comment;

    @NotNull
    private int rating;

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
            name = "client_id",
            referencedColumnName = "id",
            nullable = false
    )
    @JsonIgnore
    private Client client;
}
