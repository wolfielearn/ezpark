package com.ezpark.io.reservation.domain.port.outbound;


import com.ezpark.io.shared.kernel.SpotId;

public interface ParkingSpotAntiCorruptionService {
    boolean isSpotAvailable(SpotId spotId);
    void reserveSpot(SpotId spotId, com.ezpark.io.shared.kernel.ReservationId reservationId);
}