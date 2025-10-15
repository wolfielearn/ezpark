package com.ezpark.io.reservation.domain.port.inbound;

import com.ezpark.io.shared.event.parking.CheckInCompletedEvent;
import com.ezpark.io.shared.event.parking.CheckOutCompletedEvent;
import com.ezpark.io.shared.event.parking.SpotReleasedEvent;
import com.ezpark.io.shared.event.payment.PaymentAuthorizedEvent;

public interface ReservationEventHandler {
    public void handlePaymentAuthorized(PaymentAuthorizedEvent event);

    public void handleCheckInCompleted(CheckInCompletedEvent event);

    public void handleCheckOutCompleted(CheckOutCompletedEvent event);

    public void handleSpotReleased(SpotReleasedEvent event);
}
