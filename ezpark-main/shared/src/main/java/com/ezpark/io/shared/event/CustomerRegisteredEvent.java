package com.ezpark.io.shared.event;


import java.util.UUID;

public class CustomerRegisteredEvent extends DomainEvent {
    private final UUID customerId;
    private final String email;
    private final String name;

    public CustomerRegisteredEvent(UUID customerId, String email, String name) {
        this.customerId = customerId;
        this.email = email;
        this.name = name;
    }

    public UUID getCustomerId() { return customerId; }
    public String getEmail() { return email; }
    public String getName() { return name; }
}