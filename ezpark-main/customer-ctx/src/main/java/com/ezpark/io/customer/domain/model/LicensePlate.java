package com.ezpark.io.customer.domain.model;

public record LicensePlate(String value) {
    public LicensePlate {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("License plate cannot be empty");
        }
    }
}
