package com.ezpark.io.shared.event;


import java.util.UUID;

public class ReservationCompletedEvent extends DomainEvent {
    private UUID reservationId;
    public ReservationCompletedEvent() {
    }
    public ReservationCompletedEvent(UUID reservationId) {
        this.reservationId = reservationId;
        setEventType("ReservationCancelledEvent");
    }

    public UUID getReservationId() { return reservationId; }
}