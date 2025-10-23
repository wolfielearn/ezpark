package com.ezpark.io.shared.event;


public interface EventPublisher {
    void publish(DomainEvent event);
    void publish(DomainEvent... events);
}