package com.ezpark.io.reservation.domain.port.inbound;

import com.ezpark.io.shared.event.SpotReservedEvent;

public interface ReservationEventHandler {
    public void handleCheckInCompleted();
    public void handleCheckOutCompleted();
    public void handleSpotReleased();
    void handleSpotReserved(SpotReservedEvent event);
}
