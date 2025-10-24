package com.ezpark.io.shared.event;


import java.math.BigDecimal;
import java.util.UUID;

    public class PaymentCapturedEvent extends DomainEvent {
        private UUID paymentAuthId;
        private UUID reservationId;
        private BigDecimal capturedAmount;

        public PaymentCapturedEvent() {
        }

        public PaymentCapturedEvent(UUID paymentAuthId, UUID reservationId, BigDecimal capturedAmount) {
            this.paymentAuthId = paymentAuthId;
            this.reservationId = reservationId;
            this.capturedAmount = capturedAmount;
        }

        public UUID getPaymentAuthId() { return paymentAuthId; }
        public UUID getReservationId() { return reservationId; }
        public BigDecimal getCapturedAmount() { return capturedAmount; }
    }
