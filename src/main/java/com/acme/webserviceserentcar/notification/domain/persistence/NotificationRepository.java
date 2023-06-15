package com.acme.webserviceserentcar.notification.domain.persistence;

import com.acme.webserviceserentcar.favourite.domain.model.entity.Favourite;
import com.acme.webserviceserentcar.notification.domain.model.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Page<Notification> findByClientId(Long clientId, Pageable pageable);

}
