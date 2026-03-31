package com.ezpark.io.reservation.domain.port.inbound;

import com.ezpark.io.shared.event.SpotReservedEvent;

public interface ReservationEventHandler {
    void handleCheckInCompleted();
    void handleCheckOutCompleted();
    void handleSpotReleased();
    void handleSpotReserved(SpotReservedEvent event);
}
