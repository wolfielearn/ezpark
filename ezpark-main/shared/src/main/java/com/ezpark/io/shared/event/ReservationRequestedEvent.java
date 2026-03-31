package com.ezpark.io.shared.event;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class ReservationRequestedEvent extends DomainEvent {
    private UUID customerId;
    private UUID reservationId;
    private UUID spotId;
    private BigDecimal amount;
    private Instant startTime;
    private Instant endTime;

    public ReservationRequestedEvent() {
    }

    public ReservationRequestedEvent(UUID customerId, UUID reservationId, UUID spotId, Instant startTime, Instant endTime) {
        this.customerId = customerId;
        this.reservationId = reservationId;
        this.spotId = spotId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public UUID getCustomerId() { return customerId; }
    public UUID getSpotId() { return spotId; }
    public UUID getReservationId() {
        return reservationId;
    }
    public Instant getStartTime() { return startTime; }
    public Instant getEndTime() { return endTime; }
    public BigDecimal getAmount() {return amount; }
    @Override
    public String partitionKey() {
        if (reservationId == null) throw new IllegalStateException("reservationId is required");
        return reservationId.toString();
    }
}