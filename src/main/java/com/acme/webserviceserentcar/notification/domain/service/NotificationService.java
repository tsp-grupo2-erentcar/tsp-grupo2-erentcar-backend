package com.acme.webserviceserentcar.notification.domain.service;

import com.acme.webserviceserentcar.favourite.domain.model.entity.Favourite;
import com.acme.webserviceserentcar.notification.domain.model.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NotificationService {
    List<Notification> getAll();
    Page<Notification> getAll(Pageable pageable);

    Notification getById(Long notificationId);
    Notification create(Long clientId, Long carId, Notification request);
    ResponseEntity<?> delete(Long notificationId);
}
