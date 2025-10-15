package com.ezpark.io.customer.domain.port.inbound;


import com.ezpark.io.customer.domain.model.Email;
import com.ezpark.io.customer.domain.model.LicensePlate;
import com.ezpark.io.customer.domain.model.VehicleType;
import com.ezpark.io.shared.kernel.CustomerId;

public interface CustomerCommandService {
    CustomerId  registerCustomer(String name, Email email);
    void addVehicle(CustomerId customerId, LicensePlate licensePlate, VehicleType vehicleType);
    boolean canMakeReservations(CustomerId customerId);
}
