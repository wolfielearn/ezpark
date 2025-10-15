package com.ezpark.io.reservation.application.service;


import com.ezpark.io.reservation.domain.model.Reservation;
import com.ezpark.io.shared.kernel.ReservationId;
import java.util.Optional;

public interface ReservationQueryService {
    Optional<Reservation> findById(ReservationId reservationId);
}