package com.ezpark.io.reservation.infrastructure.mock;

import com.ezpark.io.reservation.domain.port.outbound.ParkingSpotAntiCorruptionService;
import com.ezpark.io.reservation.domain.port.outbound.model.SpotDetailsView;
import com.ezpark.io.shared.kernel.SpotId;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class MockRestParkingAntiCorruptionService implements ParkingSpotAntiCorruptionService{
    @Override
    public boolean isSpotAvailable(String  spotId) {
        return true;
    }

    @Override
    public SpotDetailsView getSpotDetails(String spotId) {
        SpotDetailsView details = new SpotDetailsView(new SpotId("spot001"),"local-A", "spot 001", true);
        return details;
    }

    @Override
    public void reserveSpot(String spotId, UUID reservationId) {
        //
    }

    @Override
    public boolean checkAvailability(String spotId, Instant startTime, Instant endTime) {
        return true;
    }
}
