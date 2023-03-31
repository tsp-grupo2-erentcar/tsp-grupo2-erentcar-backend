package com.api.rentcar.rents.domain.model.entity;

import com.api.rentcar.cars.domain.model.entity.Car;
import com.api.rentcar.users.domain.model.entity.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "reservations")
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private Date reserveDate;

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private Date returnDate;

    private Double mount;

    @NotNull
    private int status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "client_id",
            referencedColumnName = "id",
            nullable = false
    )
    @JsonIgnore
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "car_id",
            referencedColumnName = "id",
            nullable = false
    )
    @JsonIgnore
    private Car car;

    @OneToOne(mappedBy = "reservation")
    private Rent rent;
}