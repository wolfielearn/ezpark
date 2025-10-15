package com.ezpark.io.parking.application.event;


import com.ezpark.io.parking.domain.port.inbound.ParkingCommandService;
import com.ezpark.io.parking.domain.port.inbound.ParkingEventHandler;
import com.ezpark.io.shared.event.payment.PaymentCapturedEvent;
import com.ezpark.io.shared.event.reservation.ReservationCancelledEvent;
import com.ezpark.io.shared.event.reservation.ReservationConfirmedEvent;
import org.springframework.stereotype.Service;

@Service
public class ParkingEventHandlerImpl implements ParkingEventHandler {

    private final ParkingCommandService parkingCommandService;

    public ParkingEventHandlerImpl(ParkingCommandService parkingCommandService) {
        this.parkingCommandService = parkingCommandService;
    }

    @Override
    public void handleReservationConfirmed(ReservationConfirmedEvent event) {
        // Reserve the spot when reservation is confirmed
        parkingCommandService.reserveSpot(event.getSpotId(), event.getReservationId());
    }

    @Override
    public void handlePaymentCaptured(PaymentCapturedEvent event) {
        // Release spot after payment is captured
        // Note: We'll implement releaseSpot ACL method
    }

    @Override
    public void handleReservationCancelled(ReservationCancelledEvent event) {
        // Release spot if reservation is cancelled
        // Note: We'll implement releaseSpot ACL method
    }
}

