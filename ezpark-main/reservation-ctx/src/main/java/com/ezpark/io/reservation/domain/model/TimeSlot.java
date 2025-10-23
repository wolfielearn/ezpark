package com.ezpark.io.reservation.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.Instant;

@Embeddable
public record TimeSlot(
        @Column(name = "start_time") Instant startTime,
        @Column(name = "end_time") Instant endTime) {
    // JPA workaround for records
    public TimeSlot(Instant startTime, Instant endTime) {

        // Validation logic
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("Start and end time cannot be null");
        }

        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time. Got: " + startTime + " -> " + endTime);
        }

        if (java.time.Duration.between(startTime, endTime).toMinutes() < 15) {
            throw new IllegalArgumentException("Minimum reservation is 15 minutes");
        }
        this.startTime = startTime;
        this.endTime =endTime;
    }
    // Synthetic constructor for JPA
    public TimeSlot() {
        this(null, null); // JPA will set fields via reflection
    }

    private  void validate(){

    }
}