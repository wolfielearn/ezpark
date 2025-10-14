package com.ezpark.io.shared.kernel;

public record SpotId(String value) {
    public SpotId {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("SpotId cannot be null or empty");
        }
    }
}