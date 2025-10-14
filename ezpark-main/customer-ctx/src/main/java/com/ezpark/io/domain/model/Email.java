package com.ezpark.io.domain.model;

public record Email(String value) {
    public Email {
        if (value == null || !value.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }
}
