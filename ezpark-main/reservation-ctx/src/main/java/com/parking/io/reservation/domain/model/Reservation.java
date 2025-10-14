package com.parking.io.reservation.domain.model;

import com.parking.shared.kernel.CustomerId;
import com.parking.shared.kernel.PaymentAuthorizationId;
import com.parking.shared.kernel.ReservationId;
import com.parking.shared.kernel.SpotId;
import jakarta.persistence.*;

@Entity
@Table(name = "reservations")
public class Reservation {
    @EmbeddedId
    private ReservationId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "customer_id"))
    private CustomerId customerId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "spot_id"))
    private SpotId spotId;

    @Embedded
    private TimeSlot timeSlot;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "payment_auth_id"))
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
    public static Reservation create(CustomerId customerId, SpotId spotId, TimeSlot timeSlot) {
        ReservationId id = ReservationId.newId();
        return new Reservation(id, customerId, spotId, timeSlot);
    }

    // Core business logic
    public void confirm(PaymentAuthorizationId paymentAuthId) {
        if (this.status != ReservationStatus.PENDING) {
            throw new IllegalStateException("Only pending reservations can be confirmed");
        }
        this.paymentAuthId = paymentAuthId;
        this.status = ReservationStatus.CONFIRMED;
    }

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
}