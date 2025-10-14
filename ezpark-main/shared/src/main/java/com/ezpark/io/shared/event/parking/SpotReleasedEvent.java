package com.ezpark.io.shared.event.parking;


import com.ezpark.io.shared.event.DomainEvent;
import com.ezpark.io.shared.kernel.SpotId;

public class SpotReleasedEvent extends DomainEvent {
    private final SpotId spotId;

    public SpotReleasedEvent(SpotId spotId) {
        this.spotId = spotId;
    }

    public SpotId getSpotId() { return spotId; }
}