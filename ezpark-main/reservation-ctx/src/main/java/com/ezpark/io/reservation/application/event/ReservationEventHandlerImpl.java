package com.ezpark.io.reservation.application.event;

import com.ezpark.io.reservation.application.service.ReservationCommandService;
import com.ezpark.io.reservation.domain.port.inbound.ReservationEventHandler;
//import com.ezpark.io.shared.event.parking.CheckInCompletedEvent;
//import com.ezpark.io.shared.event.parking.CheckOutCompletedEvent;
//import com.ezpark.io.shared.event.parking.SpotReleasedEvent;
//import com.ezpark.io.shared.event.payment.PaymentAuthorizedEvent;
import org.jmolecules.ddd.annotation.Service;


@Service
public class ReservationEventHandlerImpl implements ReservationEventHandler {

    private final ReservationCommandService reservationCommandService;

    public ReservationEventHandlerImpl(ReservationCommandService reservationCommandService) {
        this.reservationCommandService = reservationCommandService;
    }

    @Override
    public void handlePaymentAuthorized() {

        //PaymentAuthorizedEvent event
        // Confirm reservation when payment is authorized
        //reservationCommandService.confirmReservation(event.getReservationId(), event.getPaymentAuthId());
    }

    @Override
        public void handleCheckInCompleted() {

        //CheckInCompletedEvent event
        // Mark reservation as active when customer checks in
       // reservationCommandService.markReservationAsActive(event.getReservationId());
    }

    @Override
    public void handleCheckOutCompleted() {
        //CheckOutCompletedEvent event
        // Mark reservation as completed when customer checks out
       // reservationCommandService.markReservationAsCompleted(event.getReservationId());
    }

    @Override
    public void handleSpotReleased() {
        //SpotReleasedEvent event

        // Spot released - reservation cleanup if needed
    }
}