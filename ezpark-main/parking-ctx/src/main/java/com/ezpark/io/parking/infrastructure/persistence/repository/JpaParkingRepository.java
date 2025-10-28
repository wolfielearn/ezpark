package com.ezpark.io.parking.infrastructure.persistence.repository;

import com.ezpark.io.parking.infrastructure.persistence.entities.JpaParkingSpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface JpaParkingRepository extends JpaRepository<JpaParkingSpotEntity, String> {
}
