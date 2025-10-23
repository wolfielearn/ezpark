package com.ezpark.io.reservation.infrastructure.persistence.mapper;

import com.ezpark.io.reservation.domain.model.Reservation;
import com.ezpark.io.reservation.domain.model.TimeSlot;
import com.ezpark.io.reservation.infrastructure.persistence.entities.JPAReservationEntity;
import com.ezpark.io.reservation.infrastructure.persistence.entities.VOs.JpaCustomerId;
import com.ezpark.io.reservation.infrastructure.persistence.entities.VOs.JpaPaymentAuthorizationId;
import com.ezpark.io.reservation.infrastructure.persistence.entities.VOs.JpaSpotId;
import com.ezpark.io.reservation.infrastructure.persistence.entities.VOs.JpaTimeSlot;
import com.ezpark.io.shared.kernel.CustomerId;
import com.ezpark.io.shared.kernel.SpotId;
import org.springframework.stereotype.Component;

@Component
public class JPAReservationEntityModelMapper {

    public JPAReservationEntity toEntity(Reservation reservation) {
        JPAReservationEntity entity = new JPAReservationEntity();
        entity.setId(reservation.getId().value());
        entity.setCustomerId(new JpaCustomerId(reservation.getCustomerId().value()));
        entity.setSpotId(new JpaSpotId(reservation.getSpotId().value()));
        entity.setTimeSlot(new JpaTimeSlot(
                reservation.getTimeSlot().startTime(),
                reservation.getTimeSlot().endTime()
        ));
        entity.setStatus(reservation.getStatus());
//        entity.setPaymentAuthId(new JpaPaymentAuthorizationId(
//                reservation.getPaymentAuthId().value()
//        ));
        return entity;
    }

    public Reservation toDomain(JPAReservationEntity entity) {
        return Reservation.create(
                new CustomerId(entity.getCustomerId().getValue()),
                new SpotId(entity.getSpotId().getValue()),
                new TimeSlot(
                        entity.getTimeSlot().getStartTime(),
                        entity.getTimeSlot().getEndTime()
                )
        );
    }
}
