package com.ezpark.io.shared.event;


import java.util.UUID;

public class SpotReservationFailedEvent extends DomainEvent {
    private UUID spotId;
    private UUID reservationId;
    private String message;

    public SpotReservationFailedEvent() {
    }

    public SpotReservationFailedEvent(UUID spotId, UUID reservationId, String message) {
        this.spotId = spotId;
        this.reservationId = reservationId;
        this.message =message;
    }

    public UUID getSpotId() { return spotId; }
    public UUID getReservationId() { return reservationId; }
    public String getMessage() { return message; }

    @Override
    public String partitionKey() {
        return reservationId.toString();
    }
}
