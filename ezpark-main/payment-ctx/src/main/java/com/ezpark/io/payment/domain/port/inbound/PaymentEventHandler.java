package com.ezpark.io.payment.domain.port.inbound;

import com.ezpark.io.shared.event.PaymentAuthorizationRequestedEvent;

public interface PaymentEventHandler {
    void handleReservationRequested(PaymentAuthorizationRequestedEvent event);       // From Customer Context
   // void handlePaymentAuthorizationRequested(PaymentAuthorizationRequestedEvent event); // From Reservation Context
    //void handleCheckOutCompleted(CheckOutCompletedEvent event);             // From Parking Context
    //void handleReservationCancelled(ReservationCancelledEvent event);       // From Reservation Context

}
