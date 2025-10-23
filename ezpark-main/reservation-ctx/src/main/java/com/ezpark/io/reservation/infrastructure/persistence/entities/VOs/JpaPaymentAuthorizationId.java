package com.ezpark.io.reservation.infrastructure.persistence.entities.VOs;

import java.util.UUID;

public class JpaPaymentAuthorizationId {
    private UUID value;

    public JpaPaymentAuthorizationId(){}

    public JpaPaymentAuthorizationId(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    public void setValue(UUID value) {
        this.value = value;
    }
}
