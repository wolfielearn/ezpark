package com.ezpark.io.shared.event;


import java.util.UUID;

public class SpotReservedEvent extends DomainEvent {
    private UUID spotId;
    private UUID reservationId;
    private UUID paymentAuthorizationId;

    public SpotReservedEvent() {
    }

    public SpotReservedEvent(UUID spotId, UUID reservationId, UUID paymentAuthorizationId) {
        this.spotId = spotId;
        this.reservationId = reservationId;
        this.paymentAuthorizationId = paymentAuthorizationId;
    }

    public UUID getSpotId() { return spotId; }
    public UUID getReservationId() { return reservationId; }
    public UUID getPaymentAuthorizationId() { return paymentAuthorizationId; }

    @Override
    public String partitionKey() {
        return reservationId.toString();
    }
}
