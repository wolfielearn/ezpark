package com.ezpark.io.reservation.domain.port.outbound;


import com.ezpark.io.reservation.domain.model.Reservation;
import com.ezpark.io.reservation.domain.model.ReservationStatus;
import com.ezpark.io.shared.kernel.CustomerId;
import com.ezpark.io.shared.kernel.ReservationId;
import com.ezpark.io.shared.kernel.SpotId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ReservationRepository {
    Reservation save(Reservation reservation);
    Optional<Reservation> findById(ReservationId reservationId);
    List<Reservation> findByCustomerIdAndStatus(CustomerId customerId, ReservationStatus status);
    Optional<Reservation> findActiveBySpotId(SpotId spotId);
    // Custom method NOT in JpaRepository
    List<Reservation> findByCustomerId(CustomerId customerId);

}