package com.ezpark.io.customer.infrastructure.persistence.entities;

import com.ezpark.io.customer.domain.model.VehicleType;
import jakarta.persistence.*;

import java.util.Objects;

@Embeddable
public class JpaVehicleVO {
    @Column(name = "license_plate", nullable = false, length = 20)
    private String licensePlate;

    @Column(name = "vehicle_type", nullable = false, length = 20)
    private String type;

    protected JpaVehicleVO() {}

    public JpaVehicleVO(String licensePlate, String type) {
        this.licensePlate = licensePlate;
        this.type = type;  // Just store the string
    }

    public String getLicensePlate() { return licensePlate; }
    public String getType() { return type; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof JpaVehicleVO that)) return false;
        return Objects.equals(licensePlate, that.licensePlate) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licensePlate, type);
    }
}