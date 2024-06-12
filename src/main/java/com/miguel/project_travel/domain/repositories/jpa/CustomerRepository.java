package com.miguel.project_travel.domain.repositories.jpa;

import com.miguel.project_travel.domain.entities.jpa.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {
}
