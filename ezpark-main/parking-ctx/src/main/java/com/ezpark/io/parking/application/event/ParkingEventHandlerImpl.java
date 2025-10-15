package com.ezpark.io.parking.application.event;


import com.ezpark.io.parking.domain.port.inbound.ParkingCommandService;
import com.ezpark.io.parking.domain.port.inbound.ParkingEventHandler;

import org.springframework.stereotype.Service;

@Service
public class ParkingEventHandlerImpl implements ParkingEventHandler {

    private final ParkingCommandService parkingCommandService;

    public ParkingEventHandlerImpl(ParkingCommandService parkingCommandService) {
        this.parkingCommandService = parkingCommandService;
    }

    @Override
    public void handleReservationConfirmed(Object event) {
        // Reserve the spot when reservation is confirmed
        //parkingCommandService.reserveSpot(event.getSpotId(), event.getReservationId());
    }

    @Override
    public void handlePaymentCaptured(/*PaymentCapturedEvent*/ Object event) {
        // Release spot after payment is captured
        // Note: We'll implement releaseSpot ACL method
    }

    @Override
    public void handleReservationCancelled(/*ReservationCancelledEvent*/ Object event) {
        // Release spot if reservation is cancelled
        // Note: We'll implement releaseSpot ACL method
    }
}

