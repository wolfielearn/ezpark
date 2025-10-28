package com.ezpark.io.parking.domain.model;

import com.ezpark.io.shared.kernel.ReservationId;
import com.ezpark.io.shared.kernel.SpotId;


public class ParkingSpot {

    private SpotId id;
    private Location location;
    private SpotType type;
    private SpotStatus status;
    private ReservationId currentReservationId;

    protected ParkingSpot() {}

    public ParkingSpot(SpotId id, Location location, SpotType type) {
        this.id = id;
        this.location = location;
        this.type = type;
        this.status = SpotStatus.AVAILABLE;
    }

    // In reserve(), you could publish SpotReservedEvent
    // Core business logic
    public void reserve(ReservationId reservationId) {
        if (this.status != SpotStatus.AVAILABLE) {
            throw new IllegalStateException("Spot is not available for reservation");
        }
        this.currentReservationId = reservationId;
        this.status = SpotStatus.RESERVED;
    }

    // In checkIn(), you could publish CheckInCompletedEvent
    public void checkIn() {
        if (this.status != SpotStatus.RESERVED) {
            throw new IllegalStateException("Only reserved spots can be checked in");
        }
        this.status = SpotStatus.OCCUPIED;
    }

    // In checkOut(), you could publish CheckOutCompletedEvent
    public void checkOut() {
        if (this.status != SpotStatus.OCCUPIED) {
            throw new IllegalStateException("Only occupied spots can be checked out");
        }
        this.status = SpotStatus.AVAILABLE;
        this.currentReservationId = null;
    }

    // In markForMaintenance(), you could publish SpotMaintenanceEvent
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