package com.ezpark.io.reservation.infrastructure.persistence.entities;


import com.ezpark.io.reservation.domain.model.ReservationStatus;
import com.ezpark.io.reservation.domain.model.TimeSlot;
import com.ezpark.io.reservation.infrastructure.persistence.entities.VOs.JpaCustomerId;
import com.ezpark.io.reservation.infrastructure.persistence.entities.VOs.JpaPaymentAuthorizationId;
import com.ezpark.io.reservation.infrastructure.persistence.entities.VOs.JpaSpotId;
import com.ezpark.io.reservation.infrastructure.persistence.entities.VOs.JpaTimeSlot;
import com.ezpark.io.shared.kernel.CustomerId;
import com.ezpark.io.shared.kernel.PaymentAuthorizationId;
import com.ezpark.io.shared.kernel.SpotId;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "reservation", schema = "reservation")
@AttributeOverrides({
        @AttributeOverride(name = "id.value", column = @Column(name = "reservation_id")),
        @AttributeOverride(name = "customerId.value", column = @Column(name = "customer_id")),
        @AttributeOverride(name = "spotId.value", column = @Column(name = "spot_id")),
        @AttributeOverride(name = "paymentAuthId.value", column = @Column(name = "payment_auth_id"))
})
public class JPAReservationEntity {
    @Id
    @Column(name = "reservation_id")
    private UUID id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "customer_id"))
    private JpaCustomerId customerId;  // JPA-specific VO

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "spot_id"))
    private JpaSpotId spotId;          // JPA-specific VO

    @Embedded
    private JpaTimeSlot timeSlot;      // JPA-specific VO

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReservationStatus status;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "payment_auth_id"))
    private JpaPaymentAuthorizationId paymentAuthId;  // JPA-specific VO


    public JPAReservationEntity(){}

    public JPAReservationEntity(UUID id,
                                JpaCustomerId customerId,
                                JpaSpotId spotId,
                                JpaTimeSlot timeSlot,
                                ReservationStatus status,
                                JpaPaymentAuthorizationId paymentAuthId){
        this.id = id;
        this.customerId =customerId;
        this.spotId =spotId;
        this.timeSlot =timeSlot;
        this.status = status;
        this.paymentAuthId = paymentAuthId;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public JpaCustomerId getCustomerId() {
        return customerId;
    }

    public void setCustomerId(JpaCustomerId customerId) {
        this.customerId = customerId;
    }

    public JpaSpotId getSpotId() {
        return spotId;
    }

    public void setSpotId(JpaSpotId spotId) {
        this.spotId = spotId;
    }

    public JpaTimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(JpaTimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public JpaPaymentAuthorizationId getPaymentAuthId() {
        return paymentAuthId;
    }

    public void setPaymentAuthId(JpaPaymentAuthorizationId paymentAuthId) {
        this.paymentAuthId = paymentAuthId;
    }
}
