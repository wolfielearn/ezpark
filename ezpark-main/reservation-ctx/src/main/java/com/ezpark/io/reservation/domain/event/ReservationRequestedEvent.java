package com.ezpark.io.reservation.domain.event;


import com.ezpark.io.shared.event.DomainEvent;
import com.ezpark.io.shared.kernel.CustomerId;
import com.ezpark.io.shared.kernel.SpotId;
import org.apache.kafka.common.protocol.types.Field;

import java.time.Instant;
import java.util.UUID;

public class ReservationRequestedEvent extends DomainEvent {
    private final UUID customerId;
    private final String spotId;
    private final Instant startTime;
    private final Instant endTime;

    public ReservationRequestedEvent(UUID customerId, String spotId, Instant startTime, Instant endTime) {
        this.customerId = customerId;
        this.spotId = spotId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public UUID getCustomerId() { return customerId; }
    public String getSpotId() { return spotId; }
    public Instant getStartTime() { return startTime; }
    public Instant getEndTime() { return endTime; }
}