package com.ezpark.io.shared.event;


import java.util.UUID;

public class ReservationCancelledEvent extends DomainEvent {
    private final UUID reservationId;
    private final UUID customerId;
    private final String reason;

    public ReservationCancelledEvent(UUID reservationId, UUID customerId, String reason) {
        this.reservationId = reservationId;
        this.customerId = customerId;
        this.reason = reason;
    }

    public UUID getReservationId() { return reservationId; }
    public UUID getCustomerId() { return customerId; }
    public String getReason() { return reason; }
}