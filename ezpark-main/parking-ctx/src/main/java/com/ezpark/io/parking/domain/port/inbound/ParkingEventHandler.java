package com.ezpark.io.parking.domain.port.inbound;

import com.ezpark.io.shared.event.payment.PaymentCapturedEvent;
import com.ezpark.io.shared.event.reservation.ReservationCancelledEvent;
import com.ezpark.io.shared.event.reservation.ReservationConfirmedEvent;

public interface ParkingEventHandler {

    public void handleReservationConfirmed(ReservationConfirmedEvent event);
    public void handlePaymentCaptured(PaymentCapturedEvent event);
    public void handleReservationCancelled(ReservationCancelledEvent event);


}
