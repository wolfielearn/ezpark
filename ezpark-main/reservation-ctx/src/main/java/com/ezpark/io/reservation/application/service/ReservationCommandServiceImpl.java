package com.ezpark.io.reservation.application.service;


import com.ezpark.io.reservation.domain.model.Reservation;
import com.ezpark.io.reservation.domain.model.TimeSlot;
import com.ezpark.io.reservation.domain.port.inbound.ReservationCommandService;
import com.ezpark.io.reservation.domain.port.outbound.CustomerAntiCorruptionService;
import com.ezpark.io.reservation.domain.port.outbound.ParkingSpotACLService;
import com.ezpark.io.reservation.domain.port.outbound.ReservationRepository;
import com.ezpark.io.shared.event.*;
import com.ezpark.io.shared.kernel.CustomerId;
import com.ezpark.io.shared.kernel.PaymentAuthorizationId;
import com.ezpark.io.shared.kernel.ReservationId;
import com.ezpark.io.shared.kernel.SpotId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
public class ReservationCommandServiceImpl implements ReservationCommandService {

    private final ReservationRepository reservationRepository;
    private final CustomerAntiCorruptionService customerACL;
    private final ParkingSpotACLService parkingACL;
    private final EventPublisher eventPublisher;

    public ReservationCommandServiceImpl(ReservationRepository reservationRepository,
                                         CustomerAntiCorruptionService customerACL,
                                         ParkingSpotACLService parkingACL,
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
        // use of synchronous call, because it's a query that does not change state of the parking context
        // we need to know immediately if the spot is available or not to proceed with reservation creation

        boolean isAvailable = parkingACL.checkAvailability(spotId.value(), startTime, endTime);
        if (!isAvailable) {
//            eventPublisher.publish(new ReservationFailedEvent(
//                     null, spotId, customerId, "Spot not available"
//            ));
            throw new IllegalArgumentException("Spot not available for requested time");
        }
        // 3. Create reservation
        Reservation reservation = Reservation.create(customerId, spotId, timeSlot);
        Reservation savedReservation = reservationRepository.save(reservation);

        // 4. Publish ReservationRequested
        eventPublisher.publish(new ReservationRequestedEvent(
                customerId.value(),
                savedReservation.getId().value(),
                spotId.value(),
                startTime,
                endTime
        ));
        return savedReservation.getId();
    }

    @Override
    public void confirmReservation(ReservationId reservationId, PaymentAuthorizationId paymentAuthId) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
       // Reservation savedReservation =null;
       try{
            reservation.confirm(paymentAuthId);
            reservationRepository.save(reservation);
           // Reserve the physical spot
           eventPublisher.publish(new ReservationConfirmedEvent(
                   reservation.getId().value(),
                   reservation.getPaymentAuthId().value(),
                   reservation.getSpotId().value(),
                   reservation.getCustomerId().value()));
       } catch (Exception ex){
           eventPublisher.publish(new ReservationFailedEvent(
                   reservation.getId(),
                   reservation.getSpotId(),
                   reservation.getCustomerId(),
                   ex.getMessage()));
           throw ex;
       }
    }

    @Override
    public void cancelReservation(ReservationId reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        reservation.cancel();
        reservationRepository.save(reservation);
        eventPublisher.publish(new ReservationCancelledEvent(
                reservationId.value(),
                reservation.getCustomerId().value(),
                "Reservation cancelled by customer"
        ));
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

       eventPublisher.publish(new ReservationCompletedEvent(reservationId.value()));
    }
}