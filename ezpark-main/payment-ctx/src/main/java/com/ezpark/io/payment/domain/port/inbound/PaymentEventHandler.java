package com.ezpark.io.payment.domain.port.inbound;

import com.ezpark.io.shared.event.ReservationRequestedEvent;

public interface PaymentEventHandler {
    void handleReservationRequested(ReservationRequestedEvent event); // From Reservation Context
    //void handleCheckOutCompleted(CheckOutCompletedEvent event);             // From Parking Context
    //void handleReservationCancelled(ReservationCancelledEvent event);       // From Reservation Context

}
