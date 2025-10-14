package com.parking.io.reservation.domain.model;


import jakarta.persistence.Embeddable;
import java.time.Duration;
import java.time.Instant;

@Embeddable
public record TimeSlot(Instant startTime, Instant endTime) {
    public TimeSlot {
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("Start and end time cannot be null");
        }
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
        if (Duration.between(startTime, endTime).toMinutes() < 15) {
            throw new IllegalArgumentException("Minimum reservation is 15 minutes");
        }
    }

    public Duration getDuration() {
        return Duration.between(startTime, endTime);
    }
}
