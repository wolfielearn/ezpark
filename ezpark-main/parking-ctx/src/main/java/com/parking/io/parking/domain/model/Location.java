package com.parking.io.parking.domain.model;

import jakarta.persistence.Embeddable;

@Embeddable
public record Location(String floor, String zone, String number) {
    public Location {
        if (floor == null || zone == null || number == null) {
            throw new IllegalArgumentException("Location components cannot be null");
        }
    }
}