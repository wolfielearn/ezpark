package com.ezpark.io.reservation.domain.port.outbound;


import com.ezpark.io.reservation.domain.model.Reservation;
import com.ezpark.io.shared.kernel.CustomerId;
import com.ezpark.io.shared.kernel.ReservationId;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    Reservation save(Reservation reservation);
    Optional<Reservation> findById(ReservationId reservationId);

    // Custom method NOT in JpaRepository
    List<Reservation> findByCustomerId(CustomerId customerId);

    // Another custom method
    List<Reservation> findActiveReservations();
}