package com.ezpark.io.shared.event.parking;


import com.ezpark.io.shared.event.DomainEvent;
import com.ezpark.io.shared.kernel.SpotId;

public class SpotMaintenanceEvent extends DomainEvent {
    private final SpotId spotId;
    private final boolean underMaintenance;

    public SpotMaintenanceEvent(SpotId spotId, boolean underMaintenance) {
        this.spotId = spotId;
        this.underMaintenance = underMaintenance;
    }

    public SpotId getSpotId() { return spotId; }
    public boolean isUnderMaintenance() { return underMaintenance; }
}
