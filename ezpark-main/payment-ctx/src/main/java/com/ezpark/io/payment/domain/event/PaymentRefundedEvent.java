package com.ezpark.io.payment.domain.event;


import com.ezpark.io.shared.event.DomainEvent;
import com.ezpark.io.shared.kernel.PaymentAuthorizationId;
import com.ezpark.io.shared.kernel.ReservationId;
import java.math.BigDecimal;

public class PaymentRefundedEvent extends DomainEvent {
    private final PaymentAuthorizationId paymentAuthId;
    private final ReservationId reservationId;
    private final BigDecimal refundedAmount;
    private final String refundReason;

    public PaymentRefundedEvent(PaymentAuthorizationId paymentAuthId, ReservationId reservationId,
                                BigDecimal refundedAmount, String refundReason) {
        this.paymentAuthId = paymentAuthId;
        this.reservationId = reservationId;
        this.refundedAmount = refundedAmount;
        this.refundReason = refundReason;
    }

    public PaymentAuthorizationId getPaymentAuthId() { return paymentAuthId; }
    public ReservationId getReservationId() { return reservationId; }
    public BigDecimal getRefundedAmount() { return refundedAmount; }
    public String getRefundReason() { return refundReason; }
}
