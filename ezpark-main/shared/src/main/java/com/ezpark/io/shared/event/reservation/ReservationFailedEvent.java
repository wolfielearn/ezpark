package com.ezpark.io.shared.event.reservation;


import com.ezpark.io.shared.event.DomainEvent;
import com.ezpark.io.shared.kernel.CustomerId;
import com.ezpark.io.shared.kernel.ReservationId;
import com.ezpark.io.shared.kernel.SpotId;

public class ReservationFailedEvent extends DomainEvent {
    private final ReservationId reservationId;
    private final SpotId spotId;
    private final CustomerId customerId;
    private final String failureReason;

    public ReservationFailedEvent(ReservationId reservationId, SpotId spotId,
                                  CustomerId customerId, String failureReason) {
        this.reservationId = reservationId;
        this.spotId = spotId;
        this.customerId = customerId;
        this.failureReason = failureReason;
    }

    public ReservationId getReservationId() { return reservationId; }
    public SpotId getSpotId() { return spotId; }
    public CustomerId getCustomerId() { return customerId; }
    public String getFailureReason() { return failureReason; }
}
