package com.ezpark.io.reservation.domain.port.outbound;


import com.ezpark.io.reservation.domain.model.Reservation;
import com.ezpark.io.shared.kernel.ReservationId;
import java.util.Optional;

public interface ReservationRepository {
    Reservation save(Reservation reservation);
    Optional<Reservation> findById(ReservationId reservationId);
}