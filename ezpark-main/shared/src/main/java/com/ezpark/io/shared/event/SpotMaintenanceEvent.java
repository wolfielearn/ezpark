package com.ezpark.io.shared.event;


import com.ezpark.io.shared.kernel.SpotId;

public class SpotMaintenanceEvent extends DomainEvent {
    private SpotId spotId;
    private boolean underMaintenance;

    public SpotMaintenanceEvent() {
    }

    public SpotMaintenanceEvent(SpotId spotId, boolean underMaintenance) {
        this.spotId = spotId;
        this.underMaintenance = underMaintenance;
        setEventType("SpotMaintenanceEvent");
    }

    public SpotId getSpotId() { return spotId; }
    public boolean isUnderMaintenance() { return underMaintenance; }
}
