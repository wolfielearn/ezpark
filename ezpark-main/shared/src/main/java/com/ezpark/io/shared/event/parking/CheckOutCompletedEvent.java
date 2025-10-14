package com.ezpark.io.shared.event.parking;


import com.ezpark.io.shared.event.DomainEvent;
import com.ezpark.io.shared.kernel.ReservationId;
import com.ezpark.io.shared.kernel.SpotId;
import java.time.Duration;
import java.time.Instant;

public class CheckOutCompletedEvent extends DomainEvent {
    private final ReservationId reservationId;
    private final SpotId spotId;
    private final Duration actualDuration;
    private final Instant checkOutTime;

    public CheckOutCompletedEvent(ReservationId reservationId, SpotId spotId, Duration actualDuration, Instant checkOutTime) {
        this.reservationId = reservationId;
        this.spotId = spotId;
        this.actualDuration = actualDuration;
        this.checkOutTime = checkOutTime;
    }

    public ReservationId getReservationId() { return reservationId; }
    public SpotId getSpotId() { return spotId; }
    public Duration getActualDuration() { return actualDuration; }
    public Instant getCheckOutTime() { return checkOutTime; }
}
