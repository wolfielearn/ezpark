package com.ezpark.io.reservation.infrastructure.persistence.mapper;

import com.ezpark.io.reservation.domain.model.Reservation;
import com.ezpark.io.reservation.domain.model.TimeSlot;
import com.ezpark.io.reservation.infrastructure.persistence.entities.JPAReservationEntity;
import com.ezpark.io.reservation.infrastructure.persistence.entities.VOs.JpaCustomerId;
import com.ezpark.io.reservation.infrastructure.persistence.entities.VOs.JpaPaymentAuthorizationId;
import com.ezpark.io.reservation.infrastructure.persistence.entities.VOs.JpaSpotId;
import com.ezpark.io.reservation.infrastructure.persistence.entities.VOs.JpaTimeSlot;
import com.ezpark.io.shared.kernel.CustomerId;
import com.ezpark.io.shared.kernel.PaymentAuthorizationId;
import com.ezpark.io.shared.kernel.ReservationId;
import com.ezpark.io.shared.kernel.SpotId;
import org.springframework.stereotype.Component;

import java.util.UUID;

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
        UUID paymentAuthorizationId = reservation.getPaymentAuthId() != null ? reservation.getPaymentAuthId().value() : null;
        if(paymentAuthorizationId != null ){
            entity.setPaymentAuthId(new JpaPaymentAuthorizationId(paymentAuthorizationId));
        }
        return entity;
    }

    public Reservation toDomain(JPAReservationEntity entity) {

        ReservationId reservationId = ReservationId.from(entity.getId());
        CustomerId customerId = CustomerId.from(entity.getCustomerId().getValue());
        SpotId spotId = SpotId.fromString(entity.getSpotId().getValue());
        TimeSlot timeSlot = new TimeSlot(entity.getTimeSlot().getStartTime(), entity.getTimeSlot().getEndTime());
        PaymentAuthorizationId paymentAuthorizationId = null;
        if(entity.getPaymentAuthId() != null){
            paymentAuthorizationId  = PaymentAuthorizationId.from(entity.getPaymentAuthId().getValue());
        }
       return Reservation.reconstruct(reservationId, customerId, spotId, timeSlot,paymentAuthorizationId);
    }
}
