package com.ezpark.io.shared.event;


import com.ezpark.io.shared.kernel.CustomerId;

public class CustomerRegisteredEvent extends DomainEvent {
    private CustomerId customerId;
    private String email;
    private String name;

    public CustomerRegisteredEvent() {
    }

    public CustomerRegisteredEvent(CustomerId customerId, String email, String name) {
        this.customerId = customerId;
        this.email = email;
        this.name = name;
    }

    public CustomerId getCustomerId() { return customerId; }
    public String getEmail() { return email; }
    public String getName() { return name; }

    @Override
    public String partitionKey() {
        return customerId.toString();
    }
}