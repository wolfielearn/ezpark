package com.ezpark.io.shared.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * PURE technical concern - HOW to transport events
 * No domain knowledge, just Kafka operations
 */
@Service
public class EventTransportService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public EventTransportService(KafkaTemplate<String, String> kafkaTemplate,
                                 ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * Basic send - fire and forget (for non-critical events)
     */
    public void send(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    /**
     * Guaranteed send - waits for acknowledgment (for critical events)
     */
    public void sendWithGuarantee(String topic, String message) {
        try {
            kafkaTemplate.send(topic, message).get(10, java.util.concurrent.TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send event with guarantee to topic: " + topic, e);
        }
    }

    /**
     * Pure technical serialization - no domain logic
     */
    public String serializeEvent(Object event) {
        try {
            return objectMapper.writeValueAsString(event);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize event", e);
        }
    }
}
