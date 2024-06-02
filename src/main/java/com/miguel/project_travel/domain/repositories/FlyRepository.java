package com.miguel.project_travel.domain.repositories;

import com.miguel.project_travel.domain.entities.FlyEntity;
import com.miguel.project_travel.domain.entities.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Set;

public interface FlyRepository extends JpaRepository<FlyEntity, Long> {
   /* Set<FlyEntity> selectLessPrice(BigDecimal price);
    Set<FlyEntity> selectBetweenPrice(BigDecimal min,BigDecimal max);
    Set<FlyEntity> selectOriginDestiny(String origin,String destiny);

    */
}
