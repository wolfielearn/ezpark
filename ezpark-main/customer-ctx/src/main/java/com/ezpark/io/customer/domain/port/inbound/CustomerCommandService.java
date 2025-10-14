package com.ezpark.io.customer.domain.port.inbound;


import com.ezpark.io.shared.kernel.CustomerId;

public interface CustomerCommandService {
    CustomerId registerCustomer(String name, String email);
    void addVehicle(CustomerId customerId, String licensePlate, String vehicleType);
    boolean canMakeReservations(CustomerId customerId);
}