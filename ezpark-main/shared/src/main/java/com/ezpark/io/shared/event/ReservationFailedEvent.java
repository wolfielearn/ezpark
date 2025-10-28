package com.ezpark.io.shared.event;


import com.ezpark.io.shared.kernel.CustomerId;
import com.ezpark.io.shared.kernel.ReservationId;
import com.ezpark.io.shared.kernel.SpotId;

public class ReservationFailedEvent extends DomainEvent {
    private ReservationId reservationId;
    private SpotId spotId;
    private CustomerId customerId;
    private String failureReason;

    public ReservationFailedEvent() {
    }

    public ReservationFailedEvent(ReservationId reservationId, SpotId spotId,
                                  CustomerId customerId, String failureReason) {
        this.reservationId = reservationId;
        this.spotId = spotId;
        this.customerId = customerId;
        this.failureReason = failureReason;
        setEventType("ReservationFailedEvent");
    }

    public ReservationId getReservationId() { return reservationId; }
    public SpotId getSpotId() { return spotId; }
    public CustomerId getCustomerId() { return customerId; }
    public String getFailureReason() { return failureReason; }
}
