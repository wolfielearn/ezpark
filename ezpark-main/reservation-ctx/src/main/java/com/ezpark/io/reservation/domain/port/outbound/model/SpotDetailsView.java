package com.ezpark.io.reservation.domain.port.outbound.model;


import com.ezpark.io.shared.kernel.SpotId;

public record SpotDetailsView(
        SpotId spotId,
        String location,
        String spotType,
        boolean isAvailable
) {
    public SpotDetailsView {
        if (spotId == null) throw new IllegalArgumentException("SpotId cannot be null");
        if (location == null || location.isBlank()) throw new IllegalArgumentException("Location cannot be empty");
        if (spotType == null || spotType.isBlank()) throw new IllegalArgumentException("SpotType cannot be empty");
    }
}
