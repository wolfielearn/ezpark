package com.ezpark.io.shared.event.reservation;


import com.ezpark.io.shared.event.DomainEvent;
import com.ezpark.io.shared.kernel.CustomerId;
import com.ezpark.io.shared.kernel.SpotId;
import java.time.Instant;

public class ReservationRequestedEvent extends DomainEvent {
    private final CustomerId customerId;
    private final SpotId spotId;
    private final Instant startTime;
    private final Instant endTime;

    public ReservationRequestedEvent(CustomerId customerId, SpotId spotId, Instant startTime, Instant endTime) {
        this.customerId = customerId;
        this.spotId = spotId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public CustomerId getCustomerId() { return customerId; }
    public SpotId getSpotId() { return spotId; }
    public Instant getStartTime() { return startTime; }
    public Instant getEndTime() { return endTime; }
}