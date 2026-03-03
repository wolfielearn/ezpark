package com.ezpark.io.shared.event;


import java.util.UUID;

public class SpotReservedEvent extends DomainEvent {
    private String spotId;
    private UUID reservationId;

    public SpotReservedEvent() {
    }

    public SpotReservedEvent(String spotId, UUID reservationId) {
        this.spotId = spotId;
        this.reservationId = reservationId;
    }

    public String getSpotId() { return spotId; }
    public UUID getReservationId() { return reservationId; }

    @Override
    public String partitionKey() {
        return reservationId.toString();
    }
}
