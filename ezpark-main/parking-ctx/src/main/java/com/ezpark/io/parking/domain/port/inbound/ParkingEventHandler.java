package com.ezpark.io.parking.domain.port.inbound;

//import com.ezpark.io.shared.event.payment.PaymentCapturedEvent;
//import com.ezpark.io.shared.event.reservation.ReservationCancelledEvent;
//import com.ezpark.io.shared.event.reservation.ReservationConfirmedEvent;

public interface ParkingEventHandler {

    public void handleReservationConfirmed(/*ReservationConfirmedEvent*/ Object event);
    public void handlePaymentCaptured(/*PaymentCapturedEvent */ Object event);
    public void handleReservationCancelled(/*ReservationCancelledEvent*/ Object event);


}
