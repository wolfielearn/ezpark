package com.ezpark.io.customer.domain.model;


import com.ezpark.io.customer.domain.event.CustomerRegisteredEvent;
import com.ezpark.io.shared.event.DomainEvent;
import com.ezpark.io.shared.kernel.CustomerId;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "customers")
@AttributeOverrides({
        @AttributeOverride(name = "id.value", column = @Column(name = "customer_id")),
        @AttributeOverride(name = "email.value", column = @Column(name = "email")),
})
public class Customer {
    @EmbeddedId
    private CustomerId id;

    @Embedded
    private Email email;

    private String name;

    @ElementCollection
    @CollectionTable(name = "customer_vehicles", joinColumns = @JoinColumn(name = "customer_id"))
    private List<Vehicle> vehicles = new ArrayList<>();

    @Transient
    private List<DomainEvent> domainEvents = new ArrayList<>(); //  Domain events

    protected Customer() {} // For JPA

    private Customer(CustomerId customerId, Email email, String name) {
        this.id = customerId;
        this.email = email;
        this.name = name;
        this.domainEvents.add(
                new CustomerRegisteredEvent(
                        customerId.value(),
                        name,
                        email.value())
        );

    }

    // Factory method
    public static Customer create(String name, Email email) {
        CustomerId id = CustomerId.newId();
        // Publish domain event here
        return new Customer(id, email, name);
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