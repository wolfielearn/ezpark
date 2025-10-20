package com.ezpark.io.customer.domain.model;

import jakarta.persistence.*;

import java.util.Objects;

public class Vehicle {

    private LicensePlate licensePlate;

    private VehicleType type;

    protected Vehicle() {} // For JPA

    public Vehicle(LicensePlate licensePlate, VehicleType type) {
        this.licensePlate = licensePlate;
        this.type = type;
    }

    public LicensePlate getLicensePlate() { return licensePlate; }
    public VehicleType getType() { return type; }

    // Value Object equality (all fields)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;
        return Objects.equals(licensePlate, vehicle.licensePlate) && type == vehicle.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(licensePlate, type);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "licensePlate=" + licensePlate +
                ", type=" + type +
                '}';
    }
}
