package com.ezpark.io.reservation.infrastructure.persistence.adapter;

import com.ezpark.io.reservation.domain.model.Reservation;
import com.ezpark.io.reservation.domain.model.ReservationStatus;
import com.ezpark.io.reservation.domain.port.outbound.ReservationRepository;
import com.ezpark.io.reservation.infrastructure.persistence.entities.JPAReservationEntity;
import com.ezpark.io.reservation.infrastructure.persistence.mapper.JPAReservationEntityModelMapper;
import com.ezpark.io.reservation.infrastructure.persistence.repository.JPAReservationRepository;
import com.ezpark.io.shared.kernel.CustomerId;
import com.ezpark.io.shared.kernel.ReservationId;
import com.ezpark.io.shared.kernel.SpotId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JPAReservationRepositoryAdapter implements ReservationRepository {

    private final JPAReservationRepository jpaReservationRepository;
    private final JPAReservationEntityModelMapper mapper;

    public JPAReservationRepositoryAdapter(JPAReservationRepository jpaReservationRepository, JPAReservationEntityModelMapper mapper) {
        this.jpaReservationRepository = jpaReservationRepository;
        this.mapper = mapper;
    }

    @Override
    public Reservation save(Reservation reservation) {

        JPAReservationEntity entity = mapper.toEntity(reservation);
        JPAReservationEntity savedEntity = jpaReservationRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Reservation> findById(ReservationId reservationId) {
        jpaReservationRepository.findById(reservationId.value());
        return Optional.empty();
    }

    @Override
    public List<Reservation> findByCustomerIdAndStatus(CustomerId customerId, ReservationStatus status) {
        return List.of();
    }

    @Override
    public Optional<Reservation> findActiveBySpotId(SpotId spotId) {
        return Optional.empty();
    }

    @Override
    public List<Reservation> findByCustomerId(CustomerId customerId) {
        return List.of();
    }
}
