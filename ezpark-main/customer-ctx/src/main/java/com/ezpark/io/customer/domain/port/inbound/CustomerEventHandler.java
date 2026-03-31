package com.ezpark.io.customer.domain.port.inbound;
public interface CustomerEventHandler {

    void handlePaymentCaptured();
    //void handlePaymentCaptured(PaymentCapturedEvent event);
    void handleReservationFailed();
    //void handleReservationFailed(ReservationFailedEvent event);
}