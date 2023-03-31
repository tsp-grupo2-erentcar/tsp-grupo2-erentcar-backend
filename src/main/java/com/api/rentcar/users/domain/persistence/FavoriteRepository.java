package com.api.rentcar.users.domain.persistence;

import com.api.rentcar.users.domain.model.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
    @Query(value="select*from cars where client_id=?1", nativeQuery = true)
    List<Favorite> getCarsFavoritesClientId(Long clientIdId);
}
