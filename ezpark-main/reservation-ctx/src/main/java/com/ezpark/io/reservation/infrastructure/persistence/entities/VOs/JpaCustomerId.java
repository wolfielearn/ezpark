package com.ezpark.io.reservation.infrastructure.persistence.entities.VOs;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class JpaCustomerId {


    private UUID value;

    public JpaCustomerId(){}

    public JpaCustomerId(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    public void setValue(UUID value) {
        this.value = value;
    }
}
