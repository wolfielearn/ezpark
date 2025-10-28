package com.ezpark.io.shared.event;


import com.ezpark.io.shared.kernel.SpotId;
import com.ezpark.io.shared.kernel.ReservationId;

import java.util.UUID;

public class SpotReservedEvent extends DomainEvent {
    private String spotId;
    private UUID reservationId;

    public SpotReservedEvent() {
    }

    public SpotReservedEvent(String spotId, UUID reservationId) {
        this.spotId = spotId;
        this.reservationId = reservationId;
        setEventType("SpotReservedEvent");
    }

    public String getSpotId() { return spotId; }
    public UUID getReservationId() { return reservationId; }
}
