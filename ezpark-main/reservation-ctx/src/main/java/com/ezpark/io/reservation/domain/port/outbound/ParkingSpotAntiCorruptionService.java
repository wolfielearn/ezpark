package com.ezpark.io.reservation.domain.port.outbound;


import com.ezpark.io.reservation.domain.port.outbound.model.SpotDetailsView;

import java.time.Instant;
import java.util.UUID;


public interface ParkingSpotAntiCorruptionService {
    boolean isSpotAvailable(String spotId);
    SpotDetailsView getSpotDetails(String spotId);
    void reserveSpot(String spotId, UUID reservationId);
    boolean checkAvailability(String spotId, Instant startTime, Instant endTime);

}