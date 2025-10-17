package com.ezpark.io.parking.infrastructure.mocks;

import com.ezpark.io.parking.domain.port.outbound.EventPublisher;
import com.ezpark.io.shared.event.DomainEvent;
import org.springframework.stereotype.Component;

@Component
public class MockParkingEventPublisher implements EventPublisher {
    @Override
    public void publish(DomainEvent event) {
        // send sms
    }

    @Override
    public void publish(DomainEvent... events) {
        // some logic goes here
    }
}
