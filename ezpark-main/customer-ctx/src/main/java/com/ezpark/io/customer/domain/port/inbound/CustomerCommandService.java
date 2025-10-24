package com.ezpark.io.customer.domain.port.inbound;


import com.ezpark.io.customer.domain.model.*;
import com.ezpark.io.shared.kernel.CustomerId;

public interface CustomerCommandService {
    CustomerId  registerCustomer(String name, Email email);
    Vehicle addVehicle(CustomerId customerId, LicensePlate licensePlate, VehicleType vehicleType);
    boolean canMakeReservations(CustomerId customerId);
}
