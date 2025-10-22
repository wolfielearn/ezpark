package com.ezpark.io.shared.event;


import java.util.UUID;

public class ReservationConfirmedEvent extends DomainEvent {
    private final UUID reservationId;
    private final UUID paymentAuthId;
    private final String spotId;
    private final UUID customerId;

    public ReservationConfirmedEvent(UUID reservationId, UUID paymentAuthId,
                                     String spotId, UUID customerId) {
        this.reservationId = reservationId;
        this.paymentAuthId = paymentAuthId;
        this.spotId = spotId;
        this.customerId = customerId;
    }

    public UUID getReservationId() { return reservationId; }
    public UUID getPaymentAuthId() { return paymentAuthId; }
    public String getSpotId() { return spotId; }
    public UUID getCustomerId() { return customerId; }
}