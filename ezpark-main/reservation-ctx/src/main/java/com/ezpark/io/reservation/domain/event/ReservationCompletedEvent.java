package com.ezpark.io.reservation.domain.event;


import com.ezpark.io.shared.event.DomainEvent;
import com.ezpark.io.shared.kernel.ReservationId;

import java.util.UUID;

public class ReservationCompletedEvent extends DomainEvent {
    private final UUID reservationId;

    public ReservationCompletedEvent(UUID reservationId) {
        this.reservationId = reservationId;
    }

    public UUID getReservationId() { return reservationId; }
}