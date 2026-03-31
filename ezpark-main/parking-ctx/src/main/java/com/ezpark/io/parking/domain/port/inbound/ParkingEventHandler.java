package com.ezpark.io.parking.domain.port.inbound;

import com.ezpark.io.shared.event.*;

public interface ParkingEventHandler {

    public void handleReservationConfirmed(ReservationConfirmedEvent event);
    public void handlePaymentCaptured(PaymentCapturedEvent event);
    public void handleReservationCancelled(ReservationCancelledEvent event);

    void handlePaymentAuthorizedEvent(PaymentAuthorizedEvent event);
}
