package com.ezpark.io.shared.event;


import java.math.BigDecimal;
import java.util.UUID;

public class PaymentAuthorizedEvent extends DomainEvent {
    private UUID paymentAuthId;
    private UUID reservationId;
    private UUID spotId;
    private BigDecimal authorizedAmount;

    public PaymentAuthorizedEvent() {
    }

    public PaymentAuthorizedEvent(UUID paymentAuthId, UUID reservationId, UUID spotId, BigDecimal authorizedAmount) {
        this.paymentAuthId = paymentAuthId;
        this.reservationId = reservationId;
        this.spotId = spotId;
        this.authorizedAmount = authorizedAmount;
    }

    public UUID getPaymentAuthId() { return paymentAuthId; }
    public UUID getReservationId() { return reservationId; }
    public UUID getSpotId() { return spotId; }
    public BigDecimal getAuthorizedAmount() { return authorizedAmount; }

    @Override
    public String partitionKey() {
        return reservationId.toString();
    }
}
