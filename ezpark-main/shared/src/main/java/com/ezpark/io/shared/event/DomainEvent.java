package com.ezpark.io.shared.event;


import java.time.Instant;
import java.util.UUID;

public abstract class DomainEvent {
    private final UUID eventId =  UUID.randomUUID();
    private final Instant occurredOn = Instant.now();

    public UUID getEventId() { return eventId; }
    public Instant getOccurredOn() { return occurredOn; }
    public String getEventType() {
        return getClass().getSimpleName();
    }
    /** Kafka message key for partitioning/ordering */
    public abstract String partitionKey();
}