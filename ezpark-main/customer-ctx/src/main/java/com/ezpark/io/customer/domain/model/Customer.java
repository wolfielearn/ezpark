package com.ezpark.io.customer.domain.model;


import com.ezpark.io.shared.kernel.CustomerId;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {
    @EmbeddedId
    private CustomerId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "email", unique = true))
    private Email email;

    private String name;

    @ElementCollection
    @CollectionTable(name = "customer_vehicles", joinColumns = @JoinColumn(name = "customer_id"))
    private List<Vehicle> vehicles = new ArrayList<>();

    protected Customer() {} // For JPA

    private Customer(CustomerId id, Email email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    // Factory method
    public static Customer create(String name, String email) {
        CustomerId id = CustomerId.newId();
        Email validatedEmail = new Email(email);

        // Publish domain event here

        return new Customer(id, validatedEmail, name);
    }

    // Domain method
        public void addVehicle(LicensePlate licensePlate, VehicleType type) {

            // Business invariant: Prevent duplicate vehicles
            if (vehicles.stream().anyMatch(v -> v.getLicensePlate().equals(licensePlate))) {
                throw new IllegalArgumentException("Vehicle with license plate " + licensePlate + " already exists");
            }
        Vehicle vehicle = new Vehicle(licensePlate, type);
        this.vehicles.add(vehicle);
    }

    // Business rule
    public boolean canMakeReservations() {
        return !vehicles.isEmpty();
    }

    // Getters
    public CustomerId getId() { return id; }
    public Email getEmail() { return email; }
    public String getName() { return name; }
    public List<Vehicle> getVehicles() { return Collections.unmodifiableList(vehicles); }
}