package com.ezpark.io.shared.event;


import com.ezpark.io.shared.kernel.PaymentAuthorizationId;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentRefundedEvent extends DomainEvent {
    private final PaymentAuthorizationId paymentAuthId;
    private final UUID reservationId;
    private final BigDecimal refundedAmount;
    private final String refundReason;

    public PaymentRefundedEvent(PaymentAuthorizationId paymentAuthId, UUID reservationId,
                                BigDecimal refundedAmount, String refundReason) {
        this.paymentAuthId = paymentAuthId;
        this.reservationId = reservationId;
        this.refundedAmount = refundedAmount;
        this.refundReason = refundReason;
    }

    public PaymentAuthorizationId getPaymentAuthId() { return paymentAuthId; }
    public UUID getReservationId() { return reservationId; }
    public BigDecimal getRefundedAmount() { return refundedAmount; }
    public String getRefundReason() { return refundReason; }
}
