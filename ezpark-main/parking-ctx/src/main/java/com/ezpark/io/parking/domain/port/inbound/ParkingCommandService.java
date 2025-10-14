package com.ezpark.io.parking.domain.port.inbound;


import com.ezpark.io.shared.kernel.SpotId;
import com.ezpark.io.shared.kernel.ReservationId;

public interface ParkingCommandService {
    void reserveSpot(SpotId spotId, ReservationId reservationId);
    void checkIn(SpotId spotId);
    void checkOut(SpotId spotId);
    void markSpotForMaintenance(SpotId spotId);
    void makeSpotAvailable(SpotId spotId);
}
