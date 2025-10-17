package com.ezpark.io.payment.infrastructure.mocks;

import com.ezpark.io.payment.domain.port.outbound.EventPublisher;
import com.ezpark.io.shared.event.DomainEvent;
import org.springframework.stereotype.Component;

@Component
public class MockPaymentEventPublisher implements EventPublisher {
    @Override
    public void publish(DomainEvent event) {

    }

    @Override
    public void publish(DomainEvent... events) {

    }
}
