package com.ezpark.io.parking.domain.event;


import com.ezpark.io.shared.event.DomainEvent;
import com.ezpark.io.shared.kernel.SpotId;
import com.ezpark.io.shared.kernel.ReservationId;

public class SpotReservedEvent extends DomainEvent {
    private final SpotId spotId;
    private final ReservationId reservationId;

    public SpotReservedEvent(SpotId spotId, ReservationId reservationId) {
        this.spotId = spotId;
        this.reservationId = reservationId;
    }

    public SpotId getSpotId() { return spotId; }
    public ReservationId getReservationId() { return reservationId; }
}
