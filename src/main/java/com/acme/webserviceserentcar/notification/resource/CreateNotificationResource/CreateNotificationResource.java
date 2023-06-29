package com.acme.webserviceserentcar.notification.resource.CreateNotificationResource;

import com.acme.webserviceserentcar.car.domain.model.entity.Car;
import com.acme.webserviceserentcar.client.domain.model.entity.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class CreateNotificationResource {

    @NotNull
    private String message;

    @NotNull
    private String tittle;
}
