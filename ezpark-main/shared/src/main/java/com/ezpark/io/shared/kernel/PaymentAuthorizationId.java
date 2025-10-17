package com.ezpark.io.shared.kernel;

import java.util.UUID;

public record PaymentAuthorizationId(UUID value) {

    // JPA needs this constructor for reflection
    public PaymentAuthorizationId() {
        this(UUID.randomUUID()); // or this(null) depending on your needs
    }
    public PaymentAuthorizationId {
        if (value == null) throw new IllegalArgumentException("PaymentAuthorizationId cannot be null");
    }

    public static PaymentAuthorizationId newId() {
        return new PaymentAuthorizationId(UUID.randomUUID());
    }
}