package com.ezpark.io.reservation.infrastructure.mock;

import com.ezpark.io.reservation.domain.port.outbound.EventPublisher;
import com.ezpark.io.shared.event.DomainEvent;
import org.springframework.stereotype.Component;

@Component
public class MockReservationEventPublisher implements EventPublisher {
    @Override
    public void publish(DomainEvent event) {

    }

    @Override
    public void publish(DomainEvent... events) {

    }
}
