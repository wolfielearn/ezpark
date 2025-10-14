package com.ezpark.io.reservation.domain.port.outbound;



import com.ezpark.io.shared.kernel.SpotId;
import com.ezpark.io.reservation.domain.port.outbound.model.SpotDetailsView;

public interface ParkingSpotAntiCorruptionService {
    boolean isSpotAvailable(SpotId spotId);
    SpotDetailsView getSpotDetails(SpotId spotId);
    void reserveSpot(SpotId spotId, com.ezpark.io.shared.kernel.ReservationId reservationId);
}