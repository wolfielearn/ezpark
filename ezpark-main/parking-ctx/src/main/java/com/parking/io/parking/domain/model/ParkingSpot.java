package com.parking.io.parking.domain.model;

import com.parking.shared.kernel.ReservationId;
import com.parking.shared.kernel.SpotId;
import jakarta.persistence.*;

@Entity
@Table(name = "parking_spots")
public class ParkingSpot {
    @EmbeddedId
    private SpotId id;

    @Embedded
    private Location location;

    @Enumerated(EnumType.STRING)
    private SpotType type;

    @Enumerated(EnumType.STRING)
    private SpotStatus status;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "current_reservation_id"))
    private ReservationId currentReservationId;

    protected ParkingSpot() {} // For JPA

    public ParkingSpot(SpotId id, Location location, SpotType type) {
        this.id = id;
        this.location = location;
        this.type = type;
        this.status = SpotStatus.AVAILABLE;
    }

    // Core business logic
    public void reserve(ReservationId reservationId) {
        if (this.status != SpotStatus.AVAILABLE) {
            throw new IllegalStateException("Spot is not available for reservation");
        }
        this.currentReservationId = reservationId;
        this.status = SpotStatus.RESERVED;
    }

    public void checkIn() {
        if (this.status != SpotStatus.RESERVED) {
            throw new IllegalStateException("Only reserved spots can be checked in");
        }
        this.status = SpotStatus.OCCUPIED;
    }

    public void checkOut() {
        if (this.status != SpotStatus.OCCUPIED) {
            throw new IllegalStateException("Only occupied spots can be checked out");
        }
        this.status = SpotStatus.AVAILABLE;
        this.currentReservationId = null;
    }

    public void markForMaintenance() {
        this.status = SpotStatus.MAINTENANCE;
        this.currentReservationId = null;
    }

    public void makeAvailable() {
        this.status = SpotStatus.AVAILABLE;
        this.currentReservationId = null;
    }

    // Business rule
    public boolean isAvailable() {
        return this.status == SpotStatus.AVAILABLE;
    }

    // Getters
    public SpotId getId() { return id; }
    public SpotStatus getStatus() { return status; }
    public Location getLocation() { return location; }
    public SpotType getType() { return type; }
    public ReservationId getCurrentReservationId() { return currentReservationId; }
}