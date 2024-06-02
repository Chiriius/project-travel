package com.miguel.project_travel.domain.repositories;

import com.miguel.project_travel.domain.entities.FlyEntity;
import com.miguel.project_travel.domain.entities.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TourRepository extends CrudRepository<TourEntity, Long> {
}
