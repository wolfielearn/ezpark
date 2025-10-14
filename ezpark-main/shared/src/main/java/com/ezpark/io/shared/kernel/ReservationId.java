package com.ezpark.io.shared.kernel;

import java.util.UUID;

// Cross-context identifiers
public record ReservationId(UUID value) {
    public ReservationId {
        if (value == null) throw new IllegalArgumentException("ReservationId cannot be null");
    }

    public static ReservationId newId() {
        return new ReservationId(UUID.randomUUID());
    }
}
