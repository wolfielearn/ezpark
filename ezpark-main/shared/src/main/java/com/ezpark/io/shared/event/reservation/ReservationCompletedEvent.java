package com.ezpark.io.shared.event.reservation;


import com.ezpark.io.shared.event.DomainEvent;
import com.ezpark.io.shared.kernel.ReservationId;

public class ReservationCompletedEvent extends DomainEvent {
    private final ReservationId reservationId;

    public ReservationCompletedEvent(ReservationId reservationId) {
        this.reservationId = reservationId;
    }

    public ReservationId getReservationId() { return reservationId; }
}