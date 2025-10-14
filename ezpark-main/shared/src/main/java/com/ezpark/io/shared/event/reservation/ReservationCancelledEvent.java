package com.ezpark.io.shared.event.reservation;


import com.ezpark.io.shared.event.DomainEvent;
import com.ezpark.io.shared.kernel.ReservationId;
import com.ezpark.io.shared.kernel.CustomerId;

public class ReservationCancelledEvent extends DomainEvent {
    private final ReservationId reservationId;
    private final CustomerId customerId;
    private final String reason;

    public ReservationCancelledEvent(ReservationId reservationId, CustomerId customerId, String reason) {
        this.reservationId = reservationId;
        this.customerId = customerId;
        this.reason = reason;
    }

    public ReservationId getReservationId() { return reservationId; }
    public CustomerId getCustomerId() { return customerId; }
    public String getReason() { return reason; }
}