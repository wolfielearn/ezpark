package com.parking.shared.kernel;

import java.util.UUID;

public record PaymentAuthorizationId(UUID value) {
    public PaymentAuthorizationId {
        if (value == null) throw new IllegalArgumentException("PaymentAuthorizationId cannot be null");
    }

    public static PaymentAuthorizationId newId() {
        return new PaymentAuthorizationId(UUID.randomUUID());
    }
}