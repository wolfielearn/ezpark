package com.ezpark.io.parking.application.event;


import com.ezpark.io.parking.application.service.ParkingQueryServiceImpl;
import com.ezpark.io.parking.domain.port.inbound.ParkingCommandService;
import com.ezpark.io.parking.domain.port.inbound.ParkingEventHandler;
import com.ezpark.io.shared.event.*;
import com.ezpark.io.shared.kernel.PaymentAuthorizationId;
import com.ezpark.io.shared.kernel.ReservationId;
import com.ezpark.io.shared.kernel.SpotId;
import org.springframework.stereotype.Service;

import java.util.UUID;

import java.util.UUID;

@Service
public class ParkingEventHandlerImpl implements ParkingEventHandler {

    private final ParkingCommandService parkingCommandService;
    private final ParkingQueryServiceImpl parkingQueryService ;
    private final EventPublisher eventPublisher;

    public ParkingEventHandlerImpl(ParkingCommandService parkingCommandService,
                                                        ParkingQueryServiceImpl parkingQueryService,
                                                        EventPublisher eventPublisher) {
        this.parkingCommandService = parkingCommandService;
        this.parkingQueryService = parkingQueryService;
        this.eventPublisher = eventPublisher;
    }


    @Override
    public void handlePaymentAuthorizedEvent(PaymentAuthorizedEvent event) {
        SpotId spotIdUUID = SpotId.fromUUID(event.getSpotId());
        ReservationId reservationIdUUID = ReservationId.from(UUID.fromString(event.getReservationId().toString()));
        PaymentAuthorizationId  paymentAuthorizationId =PaymentAuthorizationId.from(event.getPaymentAuthId());
        parkingCommandService.reserveSpot(spotIdUUID, reservationIdUUID, paymentAuthorizationId);
    }


    @Override
public void handleReservationConfirmed(ReservationConfirmedEvent event) {

     /*   ReservationId reservationId = ReservationId.from(event.getReservationId());
        SpotId spotId = SpotId.fromString(event.getSpotId());
        // Reserve the spot when reservation is confirmed
        parkingCommandService.reserveSpot(spotId, reservationId);*/
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

