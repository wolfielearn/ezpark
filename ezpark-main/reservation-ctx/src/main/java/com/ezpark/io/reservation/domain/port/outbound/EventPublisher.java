package com.ezpark.io.reservation.domain.port.outbound;


import com.ezpark.io.shared.event.DomainEvent;

public interface EventPublisher {
    void publish(DomainEvent event);
    void publish(DomainEvent... events);
}