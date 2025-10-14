package com.parking.shared.kernel;

import java.util.UUID;

// Cross-context identifiers
public record CustomerId(UUID value) {
    public CustomerId {
        if (value == null) throw new IllegalArgumentException("CustomerId cannot be null");
    }

    public static CustomerId newId() {
        return new CustomerId(UUID.randomUUID());
    }

    public static CustomerId fromString(String value) {
        return new CustomerId(UUID.fromString(value));
    }
}