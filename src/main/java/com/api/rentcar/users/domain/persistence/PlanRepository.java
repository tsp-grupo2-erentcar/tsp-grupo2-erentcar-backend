package com.api.rentcar.users.domain.persistence;

import com.api.rentcar.users.domain.model.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan,Long> {
}
