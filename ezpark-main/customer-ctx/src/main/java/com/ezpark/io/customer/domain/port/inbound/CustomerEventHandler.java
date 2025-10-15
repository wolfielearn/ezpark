package com.ezpark.io.customer.domain.port.inbound;

import com.ezpark.io.shared.event.payment.PaymentCapturedEvent;
import com.ezpark.io.shared.event.reservation.ReservationFailedEvent;

public interface CustomerEventHandler {
    void handlePaymentCaptured(PaymentCapturedEvent event);
    void handleReservationFailed(ReservationFailedEvent event);
}