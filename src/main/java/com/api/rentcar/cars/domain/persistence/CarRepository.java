package com.api.rentcar.cars.domain.persistence;

import com.api.rentcar.cars.domain.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    @Query(value="select*from cars where state=0", nativeQuery = true)
    List<Car> getCarsNotRents();
}
