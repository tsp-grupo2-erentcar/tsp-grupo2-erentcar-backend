package com.api.rentcar.ratings.domain.persistence;

import com.api.rentcar.ratings.domain.model.entity.RatingClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingClientRepository extends JpaRepository<RatingClient,Long> {
    @Query(value="select avg(rating) from rating_clients where client_id=?1 group by client_id; ", nativeQuery = true)
    Double getPromClientRating(Long clientId);
}
