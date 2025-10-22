package com.ezpark.io.shared.event;


import java.math.BigDecimal;
import java.util.UUID;

public class PaymentAuthorizedEvent extends DomainEvent {
    private final UUID paymentAuthId;
    private final UUID reservationId;
    private final BigDecimal authorizedAmount;

    public PaymentAuthorizedEvent(UUID paymentAuthId, UUID reservationId, BigDecimal authorizedAmount) {
        this.paymentAuthId = paymentAuthId;
        this.reservationId = reservationId;
        this.authorizedAmount = authorizedAmount;
    }

    public UUID getPaymentAuthId() { return paymentAuthId; }
    public UUID getReservationId() { return reservationId; }
    public BigDecimal getAuthorizedAmount() { return authorizedAmount; }
}
