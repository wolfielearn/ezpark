package com.ezpark.io.parking.application.service;


import com.ezpark.io.parking.domain.model.ParkingSpot;
import com.ezpark.io.parking.domain.port.inbound.ParkingCommandService;
import com.ezpark.io.parking.domain.port.outbound.ParkingSpotRepository;
import com.ezpark.io.shared.event.*;
import com.ezpark.io.shared.kernel.ReservationId;
import com.ezpark.io.shared.kernel.SpotId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ParkingCommandServiceImpl implements ParkingCommandService {

    private final ParkingSpotRepository parkingSpotRepository;
    private final EventPublisher eventPublisher;

    public ParkingCommandServiceImpl(ParkingSpotRepository parkingSpotRepository,
                                     EventPublisher eventPublisher) {
        this.parkingSpotRepository = parkingSpotRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void reserveSpot(SpotId spotId, ReservationId reservationId) {
        ParkingSpot spot = parkingSpotRepository.findById(spotId)
                .orElseThrow(() -> new IllegalArgumentException("Parking spot not found"));

        spot.reserve(reservationId);
        parkingSpotRepository.save(spot);

        eventPublisher.publish(new SpotReservedEvent(spotId.value(), reservationId.value()));
    }

    @Override
    public void checkIn(SpotId spotId) {
        ParkingSpot spot = parkingSpotRepository.findById(spotId)
                .orElseThrow(() -> new IllegalArgumentException("Parking spot not found"));

        spot.checkIn();
        parkingSpotRepository.save(spot);

//        eventPublisher.publish(new CheckInCompletedEvent(spotId, spot.getCurrentReservationId(), java.time.Instant.now()));
    }

    @Override
    public void checkOut(SpotId spotId) {
        ParkingSpot spot = parkingSpotRepository.findById(spotId)
                .orElseThrow(() -> new IllegalArgumentException("Parking spot not found"));

        // Calculate actual duration (in real system, would use actual times)
        java.time.Duration actualDuration = java.time.Duration.ofHours(2);

        spot.checkOut();
        parkingSpotRepository.save(spot);

        eventPublisher.publish(new CheckOutCompletedEvent(
                spot.getCurrentReservationId(),
                spotId,
                actualDuration,
                java.time.Instant.now()
        ));
    }

    @Override
    public void markSpotForMaintenance(SpotId spotId) {
        ParkingSpot spot = parkingSpotRepository.findById(spotId)
                .orElseThrow(() -> new IllegalArgumentException("Parking spot not found"));

        spot.markForMaintenance();
        parkingSpotRepository.save(spot);

      //  eventPublisher.publish(new SpotMaintenanceEvent(spotId, true));
    }

    @Override
    public void makeSpotAvailable(SpotId spotId) {
        ParkingSpot spot = parkingSpotRepository.findById(spotId)
                .orElseThrow(() -> new IllegalArgumentException("Parking spot not found"));

        spot.makeAvailable();
        parkingSpotRepository.save(spot);

       // eventPublisher.publish(new SpotReleasedEvent(spotId));
    }
}