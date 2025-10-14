package com.ezpark.io.parking.domain.port.inbound;


import com.ezpark.io.parking.domain.model.ParkingSpot;
import com.ezpark.io.shared.kernel.SpotId;
import java.util.List;
import java.util.Optional;

public interface ParkingQueryService {
    Optional<ParkingSpot> findById(SpotId spotId);
    List<ParkingSpot> findAllAvailableSpots();
    boolean isSpotAvailable(SpotId spotId);
}