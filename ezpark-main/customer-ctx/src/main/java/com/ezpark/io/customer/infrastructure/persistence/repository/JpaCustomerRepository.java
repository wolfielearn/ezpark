package com.ezpark.io.customer.infrastructure.persistence.repository;

import com.ezpark.io.customer.infrastructure.persistence.entities.JpaCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaCustomerRepository extends JpaRepository<JpaCustomerEntity, UUID>{

}
