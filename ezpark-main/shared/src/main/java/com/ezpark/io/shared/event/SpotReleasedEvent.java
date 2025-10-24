package com.ezpark.io.shared.event;


import com.ezpark.io.shared.kernel.SpotId;

public class SpotReleasedEvent extends DomainEvent {
    private SpotId spotId;

    public SpotReleasedEvent() {
    }

    public SpotReleasedEvent(SpotId spotId) {
        this.spotId = spotId;
    }

    public SpotId getSpotId() { return spotId; }
}