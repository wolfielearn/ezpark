package com.ezpark.io.customer.domain.event;


import com.ezpark.io.shared.event.DomainEvent;
import com.ezpark.io.shared.kernel.CustomerId;

public class CustomerRegisteredEvent extends DomainEvent {
    private final CustomerId customerId;
    private final String email;
    private final String name;

    public CustomerRegisteredEvent(CustomerId customerId, String email, String name) {
        this.customerId = customerId;
        this.email = email;
        this.name = name;
    }

    public CustomerId getCustomerId() { return customerId; }
    public String getEmail() { return email; }
    public String getName() { return name; }
}