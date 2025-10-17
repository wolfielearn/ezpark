package com.ezpark.io.reservation.application.service;


import com.ezpark.io.reservation.domain.model.TimeSlot;
import com.ezpark.io.shared.kernel.CustomerId;
import com.ezpark.io.shared.kernel.PaymentAuthorizationId;
import com.ezpark.io.shared.kernel.ReservationId;
import com.ezpark.io.shared.kernel.SpotId;

public interface ReservationCommandService {
    ReservationId createReservation(CustomerId customerId, SpotId spotId, TimeSlot timeSlot);
    void confirmReservation(ReservationId reservationId, PaymentAuthorizationId paymentAuthId);
    void cancelReservation(ReservationId reservationId);
    void markReservationAsActive(ReservationId reservationId);
    void markReservationAsCompleted(ReservationId reservationId);
}