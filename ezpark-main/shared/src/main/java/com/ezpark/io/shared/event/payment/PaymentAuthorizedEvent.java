package com.ezpark.io.shared.event.payment;


import com.ezpark.io.shared.event.DomainEvent;
import com.ezpark.io.shared.kernel.PaymentAuthorizationId;
import com.ezpark.io.shared.kernel.ReservationId;
import java.math.BigDecimal;

public class PaymentAuthorizedEvent extends DomainEvent {
    private final PaymentAuthorizationId paymentAuthId;
    private final ReservationId reservationId;
    private final BigDecimal authorizedAmount;

    public PaymentAuthorizedEvent(PaymentAuthorizationId paymentAuthId, ReservationId reservationId, BigDecimal authorizedAmount) {
        this.paymentAuthId = paymentAuthId;
        this.reservationId = reservationId;
        this.authorizedAmount = authorizedAmount;
    }

    public PaymentAuthorizationId getPaymentAuthId() { return paymentAuthId; }
    public ReservationId getReservationId() { return reservationId; }
    public BigDecimal getAuthorizedAmount() { return authorizedAmount; }
}
