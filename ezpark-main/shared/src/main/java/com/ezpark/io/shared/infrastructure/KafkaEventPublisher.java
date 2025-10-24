package com.ezpark.io.shared.infrastructure;

import com.ezpark.io.shared.event.DomainEvent;
import com.ezpark.io.shared.event.EventPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventPublisher implements EventPublisher {

    private final KafkaTemplate<String, DomainEvent> kafkaTemplate;
    @Value("${kafka.topics.reservation}")
    private String topic;
    public KafkaEventPublisher(KafkaTemplate<String, DomainEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(DomainEvent event) {
        kafkaTemplate.send(topic, event.getEventId().toString(), event);
        System.out.println("Published to Kafka - Topic: " + topic + ", Event: " + event.getClass().getSimpleName());
    }

    @Override
    public void publish(DomainEvent... events) {
        for (DomainEvent event : events) {
            publish(event);
        }
    }
}
