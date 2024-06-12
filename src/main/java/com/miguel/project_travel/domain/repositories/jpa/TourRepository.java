package com.miguel.project_travel.domain.repositories.jpa;

import com.miguel.project_travel.domain.entities.jpa.TourEntity;
import org.springframework.data.repository.CrudRepository;

public interface TourRepository extends CrudRepository<TourEntity, Long> {
}
