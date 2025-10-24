package com.ezpark.io.shared.event;


import com.ezpark.io.shared.kernel.SpotId;
import com.ezpark.io.shared.kernel.ReservationId;
import java.time.Instant;

public class CheckInCompletedEvent extends DomainEvent {
    private  SpotId spotId;
    private  ReservationId reservationId;
    private  Instant checkInTime;

    public CheckInCompletedEvent() {
    }

    public CheckInCompletedEvent(SpotId spotId, ReservationId reservationId, Instant checkInTime) {
        this.spotId = spotId;
        this.reservationId = reservationId;
        this.checkInTime = checkInTime;
    }

    public SpotId getSpotId() { return spotId; }
    public ReservationId getReservationId() { return reservationId; }
    public Instant getCheckInTime() { return checkInTime; }
}
