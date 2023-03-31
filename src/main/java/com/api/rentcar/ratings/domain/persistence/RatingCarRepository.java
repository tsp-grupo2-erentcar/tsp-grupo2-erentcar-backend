package com.api.rentcar.ratings.domain.persistence;

import com.api.rentcar.ratings.domain.model.entity.RatingCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingCarRepository extends JpaRepository<RatingCar,Long> {
    @Query(value="select avg(rating) from rating_cars where car_id=?1 group by car_id; ", nativeQuery = true)
    Double getPromCarRating(Long carId);
}
