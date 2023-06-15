package com.acme.webserviceserentcar.notification.service;

import com.acme.webserviceserentcar.car.domain.model.entity.Car;
import com.acme.webserviceserentcar.car.domain.persistence.CarRepository;
import com.acme.webserviceserentcar.client.domain.model.entity.Client;
import com.acme.webserviceserentcar.client.domain.persistence.ClientRepository;
import com.acme.webserviceserentcar.notification.domain.model.entity.Notification;
import com.acme.webserviceserentcar.notification.domain.persistence.NotificationRepository;
import com.acme.webserviceserentcar.notification.domain.service.NotificationService;
import com.acme.webserviceserentcar.shared.exception.ResourceNotFoundException;
import com.acme.webserviceserentcar.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class NotificationServiceImpl implements NotificationService {
    private static final String ENTITY = "Favourite";
    private final NotificationRepository notificationRepository;
    private final Validator validator;
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository, Validator validator, CarRepository carRepository, ClientRepository clientRepository) {
        this.notificationRepository = notificationRepository;
        this.validator = validator;
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Page<Notification> getAll(Pageable pageable) {
        return notificationRepository.findAll(pageable);
    }

    /*@Override
    public Page<Favourite> getAllFavouritesByClientId(Long clientId, Pageable pageable) {
        return favouriteRepository.findByClientId(clientId, pageable);
    }*/

    @Override
    public Notification getById(Long notificationId) {
        return notificationRepository.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ENTITY,
                        notificationId
                ));
    }

    @Override
    public Notification create(Long clientId, Long carId, Notification request) {
        Set<ConstraintViolation<Notification>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client", clientId));

        request.setClient(client);

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car", carId));

        request.setCar(car);

        return notificationRepository.save(request);
    }

    @Override
    public ResponseEntity<?> delete(Long notificationId) {
        return notificationRepository.findById(notificationId).map(favourite -> {
            notificationRepository.delete(favourite);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, notificationId));
    }
}