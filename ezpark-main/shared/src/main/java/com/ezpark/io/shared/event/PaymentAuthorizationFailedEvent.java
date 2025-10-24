package com.ezpark.io.shared.event;


import com.ezpark.io.shared.kernel.ReservationId;
import java.math.BigDecimal;

public class PaymentAuthorizationFailedEvent extends DomainEvent {
    private  ReservationId reservationId;
    private  BigDecimal attemptedAmount;
    private  String failureReason;

    public PaymentAuthorizationFailedEvent() {
    }

    public PaymentAuthorizationFailedEvent(ReservationId reservationId,
                                           BigDecimal attemptedAmount, String failureReason) {
        this.reservationId = reservationId;
        this.attemptedAmount = attemptedAmount;
        this.failureReason = failureReason;
    }

    public ReservationId getReservationId() { return reservationId; }
    public BigDecimal getAttemptedAmount() { return attemptedAmount; }
    public String getFailureReason() { return failureReason; }
}