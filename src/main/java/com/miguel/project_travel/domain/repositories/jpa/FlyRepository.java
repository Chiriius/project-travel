package com.miguel.project_travel.domain.repositories.jpa;

import com.miguel.project_travel.domain.entities.jpa.FlyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlyRepository extends JpaRepository<FlyEntity, Long> {
   /* Set<FlyEntity> selectLessPrice(BigDecimal price);
    Set<FlyEntity> selectBetweenPrice(BigDecimal min,BigDecimal max);
    Set<FlyEntity> selectOriginDestiny(String origin,String destiny);

    */
}
