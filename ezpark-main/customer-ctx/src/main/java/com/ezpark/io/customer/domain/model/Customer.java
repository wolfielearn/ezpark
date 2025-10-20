package com.ezpark.io.customer.domain.model;


import com.ezpark.io.customer.domain.event.CustomerRegisteredEvent;
import com.ezpark.io.shared.event.DomainEvent;
import com.ezpark.io.shared.kernel.CustomerId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class Customer {
    private CustomerId id;

    private Email email;

    private String name;

    private List<Vehicle> vehicles = new ArrayList<>();

    private List<DomainEvent> domainEvents = new ArrayList<>(); //  Domain events

    protected Customer() {} // For JPA

    private Customer(CustomerId id, Email email, String name) {
        this.id = Objects.requireNonNull(id, "Customer ID cannot be null");
        this.email = Objects.requireNonNull(email, "Email cannot be null");
        this.name = validateName(name);
        this.domainEvents.add(new CustomerRegisteredEvent(id.value(), name, email.value()));

    }

    // Factory method
    public static Customer create(String name, Email email) {
        CustomerId id = CustomerId.newId();
        // Publish domain event here
        return new Customer(id, email, name);
    }

    // Reconstruction constructor (for loading from persistence)
    public static Customer reconstruct(CustomerId id, Email email, String name, List<Vehicle> vehicles) {
        Customer customer = new Customer(id, email, name);
        customer.vehicles.addAll(vehicles);
        return customer;
    }

    // Domain method
    public void addVehicle(LicensePlate licensePlate, VehicleType type) {

        // Business invariant: Prevent duplicate vehicles
        if (vehicles.stream().anyMatch(v -> v.getLicensePlate().equals(licensePlate))) {
            throw new IllegalArgumentException("Vehicle with license plate " + licensePlate + " already exists");
        }
        Vehicle vehicle = new Vehicle(licensePlate, type);
        this.vehicles.add(vehicle);

            /*this.domainEvents.add(new VehicleAddedEvent(
                    id.getValue(),              // UUID primitive
                    licensePlate.getValue(),    // String primitive
                    type.name()                 // String primitive
            ));*/
     }
    public void addVehicle(Vehicle vehicle) {
        // Business invariant: Prevent duplicate vehicles
        if (vehicles.stream().anyMatch(v -> v.getLicensePlate().equals(vehicle.getLicensePlate()))) {
            throw new IllegalArgumentException("Vehicle with license plate " + vehicle.getLicensePlate() + " already exists");
        }
        this.vehicles.add(vehicle);
    }

    // Business rule
    public boolean canMakeReservations() {
        return !vehicles.isEmpty();
    }

    // Validation --> could be a VO

    private String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("Customer name cannot exceed 100 characters");
        }
        return name.trim();
    }

    // Getters
    public CustomerId getId() { return id; }
    public Email getEmail() { return email; }
    public String getName() { return name; }
    public List<Vehicle> getVehicles() { return Collections.unmodifiableList(vehicles); }
                                                                                                                                                                                                                                   // Equality (based on ID only)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", email=" + email +
                ", name='" + name + '\'' +
                ", vehicles=" + vehicles.size() +
                '}';
    }
}