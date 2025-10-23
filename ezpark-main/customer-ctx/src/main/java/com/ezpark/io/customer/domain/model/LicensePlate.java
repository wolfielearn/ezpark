package com.ezpark.io.customer.domain.model;

import com.ezpark.io.shared.kernel.CustomerId;

import java.util.UUID;

public record LicensePlate(String value) {
    public LicensePlate {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("License plate cannot be empty");
        }
    }
    public static LicensePlate fromString(String value) {
        return new LicensePlate(value);
    }

}
