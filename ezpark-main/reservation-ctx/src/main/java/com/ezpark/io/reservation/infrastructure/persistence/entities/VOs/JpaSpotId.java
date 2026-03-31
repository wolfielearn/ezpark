package com.ezpark.io.reservation.infrastructure.persistence.entities.VOs;

import java.util.UUID;

public class JpaSpotId {

    private UUID value;

    public JpaSpotId(){}

    public JpaSpotId(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    public void setValue(UUID value) {
        this.value = value;
    }
}
