package com.ezpark.io.reservation.application.event;

import com.ezpark.io.reservation.domain.port.inbound.ReservationCommandService;
import com.ezpark.io.reservation.domain.port.inbound.ReservationEventHandler;
import com.ezpark.io.shared.event.PaymentAuthorizedEvent;
import com.ezpark.io.shared.kernel.PaymentAuthorizationId;
import com.ezpark.io.shared.kernel.ReservationId;
import org.springframework.stereotype.Component;


@Component
public class ReservationEventHandlerImpl implements ReservationEventHandler {

    private final ReservationCommandService reservationCommandService;

    public ReservationEventHandlerImpl(ReservationCommandService reservationCommandService) {
        this.reservationCommandService = reservationCommandService;
    }

    @Override
    public void handlePaymentAuthorized(PaymentAuthorizedEvent event) {
        ReservationId reservationId = ReservationId.from(event.getReservationId());
        PaymentAuthorizationId paymentId = PaymentAuthorizationId.from(event.getPaymentAuthId());
        // Confirm reservation when payment is authorized
        reservationCommandService.confirmReservation(reservationId, paymentId );
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