package com.ezpark.io.shared.kernel;


import java.util.UUID;

public record SpotId(UUID value) {

    // JPA needs this constructor for reflection
    public SpotId() {
        this(null);
    }

    public SpotId {
        if (value == null || value.toString().isEmpty()) {
            throw new IllegalArgumentException("SpotId cannot be null or empty");
        }
    }
    public static SpotId newId() {
        return new SpotId(UUID.randomUUID());
    }
    public static SpotId fromUUID(UUID value) {
        return new SpotId(value);
    }
}