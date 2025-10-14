package com.ezpark.io.reservation.domain.port.outbound.model;


import com.ezpark.io.shared.kernel.CustomerId;

public record ReservationCustomerView(
        CustomerId customerId,
        String email,
        String name,
        boolean hasVehicles,
        String primaryVehicleLicensePlate
) {
    public ReservationCustomerView {
        if (customerId == null) throw new IllegalArgumentException("CustomerId cannot be null");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email cannot be empty");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be empty");
        if (primaryVehicleLicensePlate == null) {
            primaryVehicleLicensePlate = ""; // Empty if no vehicles
        }
    }
}