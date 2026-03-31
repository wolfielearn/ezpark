package com.ezpark.io.payment.domain.port.inbound;

import com.ezpark.io.shared.event.ReservationRequestedEvent;

public interface PaymentEventHandler {
    void handleReservationRequested(ReservationRequestedEvent event);
    //void handleCheckOutCompleted(CheckOutCompletedEvent event);
    //void handleReservationCancelled(ReservationCancelledEvent event);       /

}
