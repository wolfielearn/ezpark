package com.ezpark.io.parking.domain.event;


import com.ezpark.io.shared.event.DomainEvent;
import com.ezpark.io.shared.kernel.SpotId;
import com.ezpark.io.shared.kernel.ReservationId;
import java.time.Instant;

public class CheckInCompletedEvent extends DomainEvent {
    private final SpotId spotId;
    private final ReservationId reservationId;
    private final Instant checkInTime;

    public CheckInCompletedEvent(SpotId spotId, ReservationId reservationId, Instant checkInTime) {
        this.spotId = spotId;
        this.reservationId = reservationId;
        this.checkInTime = checkInTime;
    }

    public SpotId getSpotId() { return spotId; }
    public ReservationId getReservationId() { return reservationId; }
    public Instant getCheckInTime() { return checkInTime; }
}
