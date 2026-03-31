package com.ezpark.io.parking.domain.port.inbound;

import com.ezpark.io.shared.event.*;

public interface ParkingEventHandler {

    void handlePaymentAuthorizedEvent(PaymentAuthorizedEvent event);
    void handleReservationConfirmed(ReservationConfirmedEvent event);
    void handlePaymentCaptured(PaymentCapturedEvent event);
    void handleReservationCancelled(ReservationCancelledEvent event);

}
