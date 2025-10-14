package com.parking.io.payment.domain.model;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public record Amount(BigDecimal value) {
    public Amount {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }

    public Amount multiply(BigDecimal multiplier) {
        return new Amount(value.multiply(multiplier));
    }
}
