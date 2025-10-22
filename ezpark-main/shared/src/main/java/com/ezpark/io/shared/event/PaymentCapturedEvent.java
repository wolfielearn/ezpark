package com.ezpark.io.shared.event;


import java.math.BigDecimal;
import java.util.UUID;

    public class PaymentCapturedEvent extends DomainEvent {
        private final UUID paymentAuthId;
        private final UUID reservationId;
        private final BigDecimal capturedAmount;

        public PaymentCapturedEvent(UUID paymentAuthId, UUID reservationId, BigDecimal capturedAmount) {
            this.paymentAuthId = paymentAuthId;
            this.reservationId = reservationId;
            this.capturedAmount = capturedAmount;
        }

        public UUID getPaymentAuthId() { return paymentAuthId; }
        public UUID getReservationId() { return reservationId; }
        public BigDecimal getCapturedAmount() { return capturedAmount; }
    }
