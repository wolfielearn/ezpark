package com.ezpark.io.customer.domain.model;

import jakarta.persistence.*;

@Embeddable
public class Vehicle {
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "license_plate"))
    private LicensePlate licensePlate;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    protected Vehicle() {} // For JPA

    public Vehicle(LicensePlate licensePlate, VehicleType type) {
        this.licensePlate = licensePlate;
        this.type = type;
    }

    public LicensePlate getLicensePlate() { return licensePlate; }
    public VehicleType getType() { return type; }
}
