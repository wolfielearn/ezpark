package com.ezpark.io.parking.domain.port.inbound;

import com.ezpark.io.shared.event.PaymentCapturedEvent;
import com.ezpark.io.shared.event.ReservationCancelledEvent;
import com.ezpark.io.shared.event.ReservationConfirmedEvent;

public interface ParkingEventHandler {

    public void handleReservationConfirmed(ReservationConfirmedEvent event);
    public void handlePaymentCaptured(PaymentCapturedEvent event);
    public void handleReservationCancelled(ReservationCancelledEvent event);


}
