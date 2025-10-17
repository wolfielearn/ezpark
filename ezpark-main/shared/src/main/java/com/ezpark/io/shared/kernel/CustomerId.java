package com.ezpark.io.shared.kernel;

import java.util.UUID;

// Cross-context identifiers
public record CustomerId(UUID value) {

    // JPA needs this constructor for reflection
    public CustomerId() {
        this(UUID.randomUUID()); // or this(null) depending on your needs
    }
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