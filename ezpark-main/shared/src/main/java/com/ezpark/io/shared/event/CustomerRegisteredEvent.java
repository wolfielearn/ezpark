package com.ezpark.io.shared.event;


import java.util.UUID;

public class CustomerRegisteredEvent extends DomainEvent {
    private UUID customerId;
    private String email;
    private String name;

    public CustomerRegisteredEvent() {
    }

    public CustomerRegisteredEvent(UUID customerId, String email, String name) {
        this.customerId = customerId;
        this.email = email;
        this.name = name;
        setEventType("CustomerRegisteredEvent");
    }

    public UUID getCustomerId() { return customerId; }
    public String getEmail() { return email; }
    public String getName() { return name; }
}