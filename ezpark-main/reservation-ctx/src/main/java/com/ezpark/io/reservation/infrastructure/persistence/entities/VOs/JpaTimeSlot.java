package com.ezpark.io.reservation.infrastructure.persistence.entities.VOs;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.Instant;

@Embeddable
public class JpaTimeSlot {
    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;
    public JpaTimeSlot(){}

    public JpaTimeSlot(Instant startTime, Instant endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }
}
