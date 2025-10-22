package com.ezpark.io.shared.event;


import java.util.UUID;

public class ReservationCompletedEvent extends DomainEvent {
    private final UUID reservationId;

    public ReservationCompletedEvent(UUID reservationId) {
        this.reservationId = reservationId;
    }

    public UUID getReservationId() { return reservationId; }
}