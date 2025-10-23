package com.ezpark.io.reservation.infrastructure.persistence.repository;

import com.ezpark.io.reservation.infrastructure.persistence.entities.JPAReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JPAReservationRepository extends JpaRepository<JPAReservationEntity, UUID> {


}


