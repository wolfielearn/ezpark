package com.ezpark.io.reservation.domain.event;


import com.ezpark.io.shared.event.DomainEvent;
import com.ezpark.io.shared.kernel.*;

public class ReservationConfirmedEvent extends DomainEvent {
    private final ReservationId reservationId;
    private final PaymentAuthorizationId paymentAuthId;
    private final SpotId spotId;
    private final CustomerId customerId;

    public ReservationConfirmedEvent(ReservationId reservationId, PaymentAuthorizationId paymentAuthId,
                                     SpotId spotId, CustomerId customerId) {
        this.reservationId = reservationId;
        this.paymentAuthId = paymentAuthId;
        this.spotId = spotId;
        this.customerId = customerId;
    }

    public ReservationId getReservationId() { return reservationId; }
    public PaymentAuthorizationId getPaymentAuthId() { return paymentAuthId; }
    public SpotId getSpotId() { return spotId; }
    public CustomerId getCustomerId() { return customerId; }
}