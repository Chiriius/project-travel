package com.miguel.project_travel.domain.repositories;

import com.miguel.project_travel.domain.entities.CustomerEntity;
import com.miguel.project_travel.domain.entities.FlyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {
}
