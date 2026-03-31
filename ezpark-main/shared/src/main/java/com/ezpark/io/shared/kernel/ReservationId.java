package com.ezpark.io.shared.kernel;

import java.util.UUID;

public record ReservationId(UUID value) {

    public ReservationId {
        if (value == null) throw new IllegalArgumentException("ReservationId cannot be null");
    }

    public static ReservationId newId() {
        return new ReservationId(UUID.randomUUID());
    }

    public static ReservationId from(UUID value){
        return new ReservationId(value);
    }
}
