package com.api.rentcar.ratings.domain.persistence;

import com.api.rentcar.ratings.domain.model.entity.RatingOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingOwnerRepository extends JpaRepository<RatingOwner,Long> {
    @Query(value="select avg(rating) from rating_owners where owner_id=?1 group by owner_id;", nativeQuery = true)
    Double getPromOwnerRating(Long ownerId);
}
