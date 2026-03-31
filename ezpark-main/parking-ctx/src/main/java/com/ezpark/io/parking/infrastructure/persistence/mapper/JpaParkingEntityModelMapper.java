package com.ezpark.io.parking.infrastructure.persistence.mapper;

import com.ezpark.io.parking.domain.model.Location;
import com.ezpark.io.parking.domain.model.ParkingSpot;
import com.ezpark.io.parking.domain.model.SpotStatus;
import com.ezpark.io.parking.domain.model.SpotType;
import com.ezpark.io.parking.infrastructure.persistence.entities.JpaParkingSpotEntity;
import com.ezpark.io.parking.infrastructure.persistence.entities.JpaReservationId;
import com.ezpark.io.shared.kernel.ReservationId;
import com.ezpark.io.shared.kernel.SpotId;
import org.springframework.stereotype.Component;

@Component
public class JpaParkingEntityModelMapper {

    public ParkingSpot toModel(JpaParkingSpotEntity entitySpot) {

        SpotId id = SpotId.fromUUID(entitySpot.getId());
        SpotType type = entitySpot.getType();
        Location location = entitySpot.getLocation();
        SpotStatus status = entitySpot.getStatus();
        ReservationId currentReservation = null;
        if( entitySpot.getCurrentReservationId() != null){
            currentReservation  = ReservationId.from(entitySpot.getCurrentReservationId() .getValue());
        }
        // Convert domain spot to JPA entity
        return  ParkingSpot.reconstruct(id, location , type, status, currentReservation);
    }

    public JpaParkingSpotEntity toEntity(ParkingSpot spot) {
        JpaParkingSpotEntity entity =new JpaParkingSpotEntity(spot.getId().value(),spot.getLocation(),spot.getType());
        entity.setType(spot.getType());
        entity.setStatus(spot.getStatus());
        // NPE guard: currentReservationId is null when the spot is AVAILABLE (e.g. after checkOut or makeAvailable).
        // Calling .value() without this check would throw NullPointerException when saving any available spot.
        if(spot.getCurrentReservationId() != null){
            entity.setCurrentReservationId(new JpaReservationId(spot.getCurrentReservationId().value()));
        }
        // Convert JPA spot to Domain entity
        return entity;
    }
}
