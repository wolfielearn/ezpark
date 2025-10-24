package com.ezpark.io.shared.kernel;

import java.util.UUID;

// Cross-context identifiers
public record ReservationId(UUID value) {


    // JPA needs this constructor for reflection
    public ReservationId() {
        this(UUID.randomUUID()); // or this(null) depending on your needs
    }


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
