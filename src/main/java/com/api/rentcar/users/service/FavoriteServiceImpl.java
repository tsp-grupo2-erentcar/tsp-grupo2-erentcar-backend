package com.api.rentcar.users.service;

import com.api.rentcar.cars.domain.model.entity.Car;
import com.api.rentcar.cars.domain.persistence.CarRepository;
import com.api.rentcar.shared.exception.ResourceNotFoundException;
import com.api.rentcar.shared.exception.ResourceValidationException;
import com.api.rentcar.users.domain.model.entity.Client;
import com.api.rentcar.users.domain.model.entity.Favorite;
import com.api.rentcar.users.domain.persistence.ClientRepository;
import com.api.rentcar.users.domain.persistence.FavoriteRepository;
import com.api.rentcar.users.domain.service.FavoriteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    private static final String ENTITY="Favorite";
    private final FavoriteRepository favoriteRepository;
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final Validator validator;

    public FavoriteServiceImpl(FavoriteRepository favoriteRepository,
                               CarRepository carRepository, ClientRepository clientRepository,
                               Validator validator) {
        this.favoriteRepository = favoriteRepository;
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
        this.validator = validator;
    }

    @Override
    public List<Favorite> getAll() {
        return favoriteRepository.findAll();
    }

    @Override
    public Page<Favorite> getAll(Pageable pageable) {
        return favoriteRepository.findAll(pageable);
    }

    @Override
    public Favorite getById(Long favoriteId) {
        return favoriteRepository.findById(favoriteId)
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,favoriteId));
    }

    @Override
    public Favorite create(Long clientId, Long carId) {
        Client client=clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client", clientId));
        Car car=carRepository.findById(carId)
                        .orElseThrow(()->new ResourceNotFoundException("Car",carId));
        Favorite favorite=new Favorite();
        favorite.setCar(car);
        favorite.setClient(client);
        Set<ConstraintViolation<Favorite>>violations=validator.validate(favorite);
        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY,violations);
        }
        return favoriteRepository.save(favorite);
    }

    @Override
    public ResponseEntity<?> delete(Long favoriteId) {
        return favoriteRepository.findById(favoriteId).map(favorite -> {
            favoriteRepository.delete(favorite);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, favoriteId));
    }

    @Override
    public List<Favorite> getFavoritesByClient(Long clientId) {
        return favoriteRepository.getCarsFavoritesClientId(clientId);
    }
}
