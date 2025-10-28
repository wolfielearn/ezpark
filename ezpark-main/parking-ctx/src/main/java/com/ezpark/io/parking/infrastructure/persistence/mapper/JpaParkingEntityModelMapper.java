package com.ezpark.io.parking.infrastructure.persistence.mapper;

import com.ezpark.io.parking.domain.model.ParkingSpot;
import com.ezpark.io.parking.domain.model.SpotType;
import com.ezpark.io.parking.infrastructure.persistence.entities.JpaParkingSpotEntity;
import com.ezpark.io.parking.infrastructure.persistence.entities.JpaReservationId;
import com.ezpark.io.shared.kernel.SpotId;
import org.springframework.stereotype.Component;

@Component
public class JpaParkingEntityModelMapper {

    public ParkingSpot toModel(JpaParkingSpotEntity entitySpot) {

        SpotId id = SpotId.fromString(entitySpot.getId());
        SpotType type = entitySpot.getType();
        String status = entitySpot.getStatus().name();
        // Convert domain spot to JPA entity
       // ParkingSpot parkingSpot = new ParkingSpot(id, entitySpot.getLocation(), type);
        return new ParkingSpot(id,entitySpot.getLocation(), type);
    }

    public JpaParkingSpotEntity toEntity(ParkingSpot spot) {
        JpaParkingSpotEntity entity =new JpaParkingSpotEntity(spot.getId().value(),spot.getLocation(),spot.getType());
        entity.setType(spot.getType());
        entity.setStatus(spot.getStatus());
        JpaReservationId jpaReservationId = new JpaReservationId(spot.getCurrentReservationId().value());
        entity.setCurrentReservationId(jpaReservationId);
        // Convert JPA spot to Domain vehicles
        return entity;
    }
}
