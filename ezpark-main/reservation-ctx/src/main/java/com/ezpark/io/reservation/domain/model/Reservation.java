package com.ezpark.io.reservation.domain.model;

import com.ezpark.io.shared.kernel.CustomerId;
import com.ezpark.io.shared.kernel.PaymentAuthorizationId;
import com.ezpark.io.shared.kernel.ReservationId;
import com.ezpark.io.shared.kernel.SpotId;
import jakarta.persistence.*;

@Entity
@Table(name = "reservations")
@AttributeOverrides({
        @AttributeOverride(name = "id.value", column = @Column(name = "reservation_id")),
        @AttributeOverride(name = "customerId.value", column = @Column(name = "customer_id")),
        @AttributeOverride(name = "spotId.value", column = @Column(name = "spot_id")),
        @AttributeOverride(name = "paymentAuthId.value", column = @Column(name = "payment_auth_id"))
})
public class Reservation {
    @EmbeddedId
    private ReservationId id;
    @Embedded
    private CustomerId customerId;
    @Embedded
    private SpotId spotId;
    @Embedded
    private TimeSlot timeSlot;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    @Embedded
    private PaymentAuthorizationId paymentAuthId;

    protected Reservation() {} // For JPA

    private Reservation(ReservationId id, CustomerId customerId, SpotId spotId, TimeSlot timeSlot) {
        this.id = id;
        this.customerId = customerId;
        this.spotId = spotId;
        this.timeSlot = timeSlot;
        this.status = ReservationStatus.PENDING;
    }

    // Factory method
    public static Reservation create(CustomerId customerId, SpotId  spotId, TimeSlot timeSlot) {
        ReservationId id = ReservationId.newId();
        return new Reservation(id, customerId, spotId, timeSlot);
    }


    // In confirm(), you could publish ReservationConfirmedEvent

    // Core business logic
    public void confirm(PaymentAuthorizationId paymentAuthId) {
        if (this.status != ReservationStatus.PENDING) {
            throw new IllegalStateException("Only pending reservations can be confirmed");
        }
        this.paymentAuthId = paymentAuthId;
        this.status = ReservationStatus.CONFIRMED;
    }

    // In cancel(), you could publish ReservationCancelledEvent
    public void cancel() {
        if (this.status == ReservationStatus.COMPLETED || this.status == ReservationStatus.ACTIVE) {
            throw new IllegalStateException("Cannot cancel completed or active reservation");
        }
        this.status = ReservationStatus.CANCELLED;
    }

    public void markAsActive() {
        if (this.status != ReservationStatus.CONFIRMED) {
            throw new IllegalStateException("Only confirmed reservations can be activated");
        }
        this.status = ReservationStatus.ACTIVE;
    }

    // In markAsCompleted(), you could publish ReservationCompletedEvent
    public void markAsCompleted() {
        if (this.status != ReservationStatus.ACTIVE) {
            throw new IllegalStateException("Only active reservations can be completed");
        }
        this.status = ReservationStatus.COMPLETED;
    }

    // Getters
    public ReservationId getId() { return id; }
    public CustomerId getCustomerId() { return customerId; }
    public SpotId getSpotId() { return spotId; }
    public ReservationStatus getStatus() { return status; }
    public TimeSlot getTimeSlot() { return timeSlot; }

    public PaymentAuthorizationId getPaymentAuthId() { return paymentAuthId; }
}