package com.ezpark.io.payment.domain.port.inbound;

import com.ezpark.io.shared.event.PaymentAuthorizationRequestedEvent;

public interface PaymentEventHandler {
    void handlePaymentAuthorizationRequested(PaymentAuthorizationRequestedEvent event); // From Reservation Context
    //void handleReservationRequested();       // From Customer Context
    //void handleCheckOutCompleted(CheckOutCompletedEvent event);             // From Parking Context
    //void handleReservationCancelled(ReservationCancelledEvent event);       // From Reservation Context

}
