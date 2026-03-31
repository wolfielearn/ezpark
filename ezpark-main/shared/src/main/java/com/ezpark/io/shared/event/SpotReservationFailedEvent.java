package com.ezpark.io.shared.event;


import java.util.UUID;

public class SpotReservationFailedEvent extends DomainEvent {
    private UUID spotId;
    private UUID reservationId;
    private String failureReason;

    public SpotReservationFailedEvent() {
    }

    public SpotReservationFailedEvent(UUID spotId, UUID reservationId, String failureReason) {
        this.spotId = spotId;
        this.reservationId = reservationId;
        this.failureReason = failureReason;
    }

    public UUID getSpotId() { return spotId; }
    public UUID getReservationId() { return reservationId; }
    public String getFailureReason() { return failureReason; }

    @Override
    public String partitionKey() {
        return reservationId.toString();
    }
}
