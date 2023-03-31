package com.api.rentcar.ratings.domain.service;

import com.api.rentcar.ratings.domain.model.entity.RatingOwner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RatingOwnerService {
    List<RatingOwner> getAll();
    Page<RatingOwner> getAll(Pageable pageable);
    RatingOwner getById(Long ratingOwnerId);
    RatingOwner create(Long ownerId,Long clientId,RatingOwner ratingOwner);
    ResponseEntity<?> delete(Long ratingOwnerId);
}
