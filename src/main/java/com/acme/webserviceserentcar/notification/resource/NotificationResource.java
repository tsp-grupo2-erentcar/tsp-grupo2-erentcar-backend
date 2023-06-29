package com.acme.webserviceserentcar.notification.resource;

import com.acme.webserviceserentcar.car.resource.CarResource;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class NotificationResource {
    private Long id;
    private Long clientId;
    private CarResource car;
    private String message;
    private String tittle;
}
