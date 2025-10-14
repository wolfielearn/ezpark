package com.ezpark.io.domain.model;

public record LicensePlate(String value) {
    public LicensePlate {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("License plate cannot be empty");
        }
    }
}
