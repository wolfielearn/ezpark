package com.ezpark.io.shared.kernel;


public record SpotId(String value) {

    // JPA needs this constructor for reflection
    public SpotId() {
        this(null);
    }

    public SpotId {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("SpotId cannot be null or empty");
        }
    }

    public static SpotId fromString(String value) {
        return new SpotId(value);
    }
}