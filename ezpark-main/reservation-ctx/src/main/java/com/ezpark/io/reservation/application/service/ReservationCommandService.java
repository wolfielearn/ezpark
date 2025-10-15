package com.ezpark.io.reservation.application.service;


import com.ezpark.io.shared.kernel.ReservationId;
import com.ezpark.io.shared.kernel.CustomerId;
import com.ezpark.io.shared.kernel.SpotId;
import java.time.Instant;

public interface ReservationCommandService {
    ReservationId createReservation(CustomerId customerId, SpotId spotId, Instant startTime, Instant endTime);
    void confirmReservation(ReservationId reservationId, com.ezpark.io.shared.kernel.PaymentAuthorizationId paymentAuthId);
    void cancelReservation(ReservationId reservationId);
    void markReservationAsActive(ReservationId reservationId);
    void markReservationAsCompleted(ReservationId reservationId);
}