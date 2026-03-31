package com.ezpark.io.reservation.domain.port.outbound;


import com.ezpark.io.reservation.domain.port.outbound.model.SpotDetailsView;

import java.time.Instant;
import java.util.UUID;


public interface ParkingSpotACLService {
    boolean isSpotAvailable(String spotId);
    SpotDetailsView getSpotDetails(String spotId);
    boolean reserveSpot(String spotId, UUID reservationId);
    boolean checkAvailability(UUID spotId, Instant startTime, Instant endTime);

}