package com.ezpark.io.shared.event.payment;


import com.ezpark.io.shared.event.DomainEvent;
import com.ezpark.io.shared.kernel.PaymentAuthorizationId;
import com.ezpark.io.shared.kernel.ReservationId;
import java.math.BigDecimal;

public class PaymentCapturedEvent extends DomainEvent {
    private final PaymentAuthorizationId paymentAuthId;
    private final ReservationId reservationId;
    private final BigDecimal capturedAmount;

    public PaymentCapturedEvent(PaymentAuthorizationId paymentAuthId, ReservationId reservationId, BigDecimal capturedAmount) {
        this.paymentAuthId = paymentAuthId;
        this.reservationId = reservationId;
        this.capturedAmount = capturedAmount;
    }

    public PaymentAuthorizationId getPaymentAuthId() { return paymentAuthId; }
    public ReservationId getReservationId() { return reservationId; }
    public BigDecimal getCapturedAmount() { return capturedAmount; }
}
