package com.ezpark.io.payment.domain.model;

import com.ezpark.io.shared.kernel.PaymentAuthorizationId;
import com.ezpark.io.shared.kernel.ReservationId;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

@Entity
@Table(name = "payment_authorizations")
public class PaymentAuthorization {
    @EmbeddedId
    private PaymentAuthorizationId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "reservation_id"))
    private ReservationId reservationId;

    @Embedded
    private Amount amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    private Instant authorizedAt;
    private Instant capturedAt;

    protected PaymentAuthorization() {} // For JPA

    private PaymentAuthorization(PaymentAuthorizationId id, ReservationId reservationId,
                                 Amount amount, PaymentMethod method) {
        this.id = id;
        this.reservationId = reservationId;
        this.amount = amount;
        this.method = method;
        this.status = PaymentStatus.AUTHORIZED;
        this.authorizedAt = Instant.now();
    }

    // Factory method
    public static PaymentAuthorization create(ReservationId reservationId, Amount amount, PaymentMethod method) {
        PaymentAuthorizationId id = PaymentAuthorizationId.newId();
        return new PaymentAuthorization(id, reservationId, amount, method);
    }

    // Core business logic
    public void capture() {
        if (this.status != PaymentStatus.AUTHORIZED) {
            throw new IllegalStateException("Only authorized payments can be captured");
        }
        this.status = PaymentStatus.CAPTURED;
        this.capturedAt = Instant.now();
    }

    public void expire() {
        if (this.status == PaymentStatus.AUTHORIZED) {
            this.status = PaymentStatus.EXPIRED;
        }
    }

    public void refund() {
        if (this.status != PaymentStatus.CAPTURED) {
            throw new IllegalStateException("Only captured payments can be refunded");
        }
        this.status = PaymentStatus.REFUNDED;
    }

    // Business rule: Authorization expires after 24 hours
    public boolean isExpired() {
        return this.status == PaymentStatus.AUTHORIZED &&
                Duration.between(authorizedAt, Instant.now()).toHours() >= 24;
    }

    // Getters
    public PaymentAuthorizationId getId() { return id; }
    public ReservationId getReservationId() { return reservationId; }
    public PaymentStatus getStatus() { return status; }
    public Amount getAmount() { return amount; }
    public PaymentMethod getMethod() { return method; }
    public Instant getAuthorizedAt() { return authorizedAt; }
}