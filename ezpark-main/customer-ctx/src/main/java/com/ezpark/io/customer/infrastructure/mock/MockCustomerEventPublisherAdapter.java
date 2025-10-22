package com.ezpark.io.customer.infrastructure.mock;

import com.ezpark.io.customer.domain.port.outbound.EventPublisher;
import com.ezpark.io.shared.event.DomainEvent;
import org.springframework.stereotype.Component;

@Component
public class MockCustomerEventPublisherAdapter implements EventPublisher {
    @Override
    public void publish(DomainEvent event) {

    }

    @Override
    public void publish(DomainEvent... events) {

    }
}
