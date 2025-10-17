package com.ezpark.io.shared.kernel;


public record SpotId(String value) {

    // JPA needs this constructor for reflection
    public SpotId() {
        this(null); // or this(null) depending on your needs
    }

    public SpotId {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("SpotId cannot be null or empty");
        }
    }
}