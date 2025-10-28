package com.ezpark.io.shared.event;


import java.math.BigDecimal;
import java.util.UUID;

public class PaymentAuthorizedEvent extends DomainEvent {
    private UUID paymentAuthId;
    private UUID reservationId;
    private BigDecimal authorizedAmount;

    public PaymentAuthorizedEvent() {
    }

    public PaymentAuthorizedEvent(UUID paymentAuthId, UUID reservationId, BigDecimal authorizedAmount) {
        this.paymentAuthId = paymentAuthId;
        this.reservationId = reservationId;
        this.authorizedAmount = authorizedAmount;
        setEventType("PaymentAuthorizedEvent");
    }


    public UUID getPaymentAuthId() { return paymentAuthId; }
    public UUID getReservationId() { return reservationId; }
    public BigDecimal getAuthorizedAmount() { return authorizedAmount; }
}
