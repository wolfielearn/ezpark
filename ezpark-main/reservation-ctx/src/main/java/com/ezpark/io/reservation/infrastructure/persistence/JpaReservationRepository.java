package com.ezpark.io.reservation.infrastructure.persistence;

import com.ezpark.io.reservation.domain.model.Reservation;
import com.ezpark.io.reservation.domain.model.ReservationStatus;
import com.ezpark.io.reservation.domain.port.outbound.ReservationRepository;
import com.ezpark.io.shared.kernel.CustomerId;
import com.ezpark.io.shared.kernel.ReservationId;
import com.ezpark.io.shared.kernel.SpotId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaReservationRepository
        extends JpaRepository<Reservation, ReservationId>, ReservationRepository {

    @Override
    Reservation save(Reservation reservation);

    @Override
    List<Reservation> findByCustomerIdAndStatus(CustomerId customerId, ReservationStatus status);

    // Add missing method
    @Override
    @Query("SELECT r FROM Reservation r WHERE r.spotId = :spotId AND r.status = 'ACTIVE'")
    Optional<Reservation> findActiveBySpotId(@Param("spotId") SpotId spotId);

    //  Keep your custom method (if needed separately)
    List<Reservation> findByCustomerId(CustomerId customerId);

    // Optional: Additional query methods
    List<Reservation> findByStatusAndCustomerId(ReservationStatus status, CustomerId customerId);



}


