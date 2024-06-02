package com.miguel.project_travel.domain.repositories;

import com.miguel.project_travel.domain.entities.FlyEntity;
import com.miguel.project_travel.domain.entities.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReservationRepository extends CrudRepository<ReservationEntity, UUID> {
}
