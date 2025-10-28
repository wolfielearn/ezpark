package com.ezpark.io.shared.event;


public class SpotReleasedEvent extends DomainEvent {
    private String spotId;

    public SpotReleasedEvent() {
    }

    public SpotReleasedEvent(String spotId) {
        this.spotId = spotId;
        setEventType("SpotReleasedEvent");
    }

    public String getSpotId() { return spotId; }
}