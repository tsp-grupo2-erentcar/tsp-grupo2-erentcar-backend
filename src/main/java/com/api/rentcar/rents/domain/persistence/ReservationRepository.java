package com.api.rentcar.rents.domain.persistence;

import com.api.rentcar.rents.domain.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    @Query(value="select R.* from reservations as R " +
            "left join cars on cars.id=R.car_id " +
            "where cars.owner_id=?1 group by R.id", nativeQuery = true)
    List<Reservation> getReservationsByOwner(Long ownerId);
    @Query(value="select * from reservations where client_id=?1 ", nativeQuery = true)
    List<Reservation> getReservationsByClient(Long clientId);
}
