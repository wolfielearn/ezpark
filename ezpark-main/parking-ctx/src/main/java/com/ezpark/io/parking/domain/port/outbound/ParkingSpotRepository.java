package com.ezpark.io.parking.domain.port.outbound;


import com.ezpark.io.parking.domain.model.ParkingSpot;
import com.ezpark.io.shared.kernel.SpotId;
import java.util.List;
import java.util.Optional;

public interface ParkingSpotRepository {
    ParkingSpot save(ParkingSpot parkingSpot);
    Optional<ParkingSpot> findById(SpotId spotId);
    List<ParkingSpot> findAll();
    List<ParkingSpot> findByStatus(com.ezpark.io.parking.domain.model.SpotStatus status);
}