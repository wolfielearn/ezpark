package com.ezpark.io.reservation.application.service;


import com.ezpark.io.reservation.domain.model.Reservation;
import com.ezpark.io.reservation.domain.model.TimeSlot;
import com.ezpark.io.reservation.domain.port.inbound.ReservationCommandService;
import com.ezpark.io.reservation.domain.port.outbound.CustomerAntiCorruptionService;
import com.ezpark.io.reservation.domain.port.outbound.ParkingSpotAntiCorruptionService;
import com.ezpark.io.reservation.domain.port.outbound.ReservationRepository;
import com.ezpark.io.shared.event.*;
import com.ezpark.io.shared.kernel.CustomerId;
import com.ezpark.io.shared.kernel.PaymentAuthorizationId;
import com.ezpark.io.shared.kernel.ReservationId;
import com.ezpark.io.shared.kernel.SpotId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@Transactional
public class ReservationCommandServiceImpl implements ReservationCommandService {

    private final ReservationRepository reservationRepository;
    private final CustomerAntiCorruptionService customerACL;
    private final ParkingSpotAntiCorruptionService parkingACL;
    private final EventPublisher eventPublisher;

    public ReservationCommandServiceImpl(ReservationRepository reservationRepository,
                                         CustomerAntiCorruptionService customerACL,
                                         ParkingSpotAntiCorruptionService parkingACL,
                                         EventPublisher eventPublisher) {
        this.reservationRepository = reservationRepository;
        this.customerACL = customerACL;
        this.parkingACL = parkingACL;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public ReservationId createReservation(CustomerId customerId, SpotId spotId, TimeSlot timeSlot) {

       // 1. Validate customer can make reservations
        var customerView = customerACL.getCustomerForReservation(customerId);
        if (!customerView.hasVehicles()) {
            throw new IllegalArgumentException("Customer must have vehicles to make reservations");
        }
        Instant startTime = timeSlot.startTime();
        Instant endTime = timeSlot.endTime();
        // 2. Check spot availability
        boolean isAvailable = parkingACL.checkAvailability(spotId.value(), startTime, endTime);
        if (!isAvailable) {
//            eventPublisher.publish(new ReservationFailedEvent(
//                    ReservationId.newId(), spotId, customerId, "Spot not available"
//            ));
            throw new IllegalArgumentException("Spot not available for requested time");
        }

        // 3. Create reservation

        Reservation reservation = Reservation.create(customerId, spotId, timeSlot);
        Reservation savedReservation = reservationRepository.save(reservation);

        // 4. Request payment authorization
        eventPublisher.publish(new PaymentAuthorizationRequestedEvent(
                savedReservation.getId().value().toString(),
                customerId.value().toString(),
                spotId.value(),
                BigDecimal.valueOf(5),
                startTime, endTime
        ));

        return savedReservation.getId();
    }

    @Override
    public void confirmReservation(ReservationId reservationId, PaymentAuthorizationId paymentAuthId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        reservation.confirm(paymentAuthId);
        reservationRepository.save(reservation);

        // Reserve the physical spot
        parkingACL.reserveSpot(reservation.getSpotId().value(), reservationId.value());

//        eventPublisher.publish(new SpotReservedEvent(
//                reservationId.value(),
//                paymentAuthId.value(), ));
    }

    @Override
    public void cancelReservation(ReservationId reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        reservation.cancel();
        reservationRepository.save(reservation);

//        eventPublisher.publish(new ReservationCancelledEvent(
//                reservationId.value(),
//                reservation.getCustomerId().value(),
//                "Customer cancelled"
//        ));
    }

    @Override
    public void markReservationAsActive(ReservationId reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        reservation.markAsActive();
        reservationRepository.save(reservation);
    }

    @Override
    public void markReservationAsCompleted(ReservationId reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        reservation.markAsCompleted();
        reservationRepository.save(reservation);

//        eventPublisher.publish(new ReservationCompletedEvent(reservationId.value()));
    }
}