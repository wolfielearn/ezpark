package com.ezpark.io.parking.infrastructure.persistence.entities;

import java.util.UUID;

public class JpaReservationId {
    private UUID value;

    // JPA needs this constructor for reflection
    public JpaReservationId() {}


    public JpaReservationId(UUID value) {
        if (value == null) throw new IllegalArgumentException("ReservationId cannot be null");
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    public void setValue(UUID value) {
        this.value = value;
    }
}
