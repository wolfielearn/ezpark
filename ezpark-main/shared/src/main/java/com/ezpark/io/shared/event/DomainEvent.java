package com.ezpark.io.shared.event;


import java.time.Instant;
import java.util.UUID;

public abstract class DomainEvent {
    private final UUID eventId;
    private final Instant occurredOn;
    private String eventType;

    protected DomainEvent() {
        this.eventId = UUID.randomUUID();
        this.occurredOn = Instant.now();
    }
    public UUID getEventId() { return eventId; }
    public Instant getOccurredOn() { return occurredOn; }

    public String getEventType() {
        return eventType;
    }
    public void  setEventType(String eventType) { this.eventType = eventType;
    }
}