package com.ezpark.io.shared.event;


import com.ezpark.io.shared.kernel.PaymentAuthorizationId;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentRefundedEvent extends DomainEvent {
    private PaymentAuthorizationId paymentAuthId;
    private UUID reservationId;
    private BigDecimal refundedAmount;
    private String refundReason;

    public PaymentRefundedEvent() {
    }

    public PaymentRefundedEvent(PaymentAuthorizationId paymentAuthId, UUID reservationId,
                                BigDecimal refundedAmount, String refundReason) {
        this.paymentAuthId = paymentAuthId;
        this.reservationId = reservationId;
        this.refundedAmount = refundedAmount;
        this.refundReason = refundReason;
        setEventType("PaymentRefundedEvent");
    }

    public PaymentAuthorizationId getPaymentAuthId() { return paymentAuthId; }
    public UUID getReservationId() { return reservationId; }
    public BigDecimal getRefundedAmount() { return refundedAmount; }
    public String getRefundReason() { return refundReason; }
}
