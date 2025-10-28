package com.ezpark.io.shared.event;


import com.ezpark.io.shared.kernel.SpotId;
import com.ezpark.io.shared.kernel.ReservationId;

public class SpotReservedEvent extends DomainEvent {
    private SpotId spotId;
    private ReservationId reservationId;

    public SpotReservedEvent() {
    }

    public SpotReservedEvent(SpotId spotId, ReservationId reservationId) {
        this.spotId = spotId;
        this.reservationId = reservationId;
        setEventType("SpotReservedEvent");
    }

    public SpotId getSpotId() { return spotId; }
    public ReservationId getReservationId() { return reservationId; }
}
