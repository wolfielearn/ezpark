package com.ezpark.io.customer.infrastructure.persistence.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class JpaLicensePlateVO {

    @Column(name = "license_plate", nullable = false, length = 20)
    private String value;

    protected JpaLicensePlateVO() {}

    public JpaLicensePlateVO(String value) {
        this.value = validateLicensePlate(value);
    }

    private String validateLicensePlate(String licensePlate) {
        if (licensePlate == null || licensePlate.isBlank()) {
            throw new IllegalArgumentException("License plate cannot be null or empty");
        }
        String trimmed = licensePlate.trim().toUpperCase();
        if (!trimmed.matches("^[A-Z0-9]{2,10}$")) {
            throw new IllegalArgumentException("Invalid license plate format: " + licensePlate);
        }
        return trimmed;
    }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JpaLicensePlateVO that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "JpaLicensePlateVO{value='" + value + "'}";
    }
}