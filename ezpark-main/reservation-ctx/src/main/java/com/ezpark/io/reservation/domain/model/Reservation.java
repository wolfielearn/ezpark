package com.ezpark.io.reservation.domain.model;

import com.ezpark.io.shared.kernel.CustomerId;
import com.ezpark.io.shared.kernel.PaymentAuthorizationId;
import com.ezpark.io.shared.kernel.ReservationId;
import com.ezpark.io.shared.kernel.SpotId;


public class Reservation {
    private ReservationId id;
    private CustomerId customerId;
    private SpotId spotId;
    private TimeSlot timeSlot;
    private ReservationStatus status;
    private PaymentAuthorizationId paymentAuthId;

    protected Reservation() {} // For JPA

    private Reservation(ReservationId id, CustomerId customerId, SpotId spotId, TimeSlot timeSlot, PaymentAuthorizationId paymentAuthId) {
        this.id = id;
        this.customerId = customerId;
        this.spotId = spotId;
        this.timeSlot = timeSlot;
        this.status = ReservationStatus.PENDING;
        this.paymentAuthId = paymentAuthId;
    }

    // Factory method
    public static Reservation create(CustomerId customerId, SpotId  spotId, TimeSlot timeSlot) {
        ReservationId id = ReservationId.newId();
        return new Reservation(id, customerId, spotId, timeSlot, null);
    }
    // Reconstruction constructor (for loading from persistence)
    public static Reservation reconstruct(ReservationId id, CustomerId costumerId, SpotId spotId, TimeSlot timeSlot, PaymentAuthorizationId paymentAuthId) {
        return new Reservation(id, costumerId, spotId, timeSlot, paymentAuthId);
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