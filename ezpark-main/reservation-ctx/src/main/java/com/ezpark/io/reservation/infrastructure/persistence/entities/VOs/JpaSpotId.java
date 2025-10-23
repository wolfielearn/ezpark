package com.ezpark.io.reservation.infrastructure.persistence.entities.VOs;

import java.util.UUID;

public class JpaSpotId {

    private String value;

    public JpaSpotId(){}

    public JpaSpotId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
