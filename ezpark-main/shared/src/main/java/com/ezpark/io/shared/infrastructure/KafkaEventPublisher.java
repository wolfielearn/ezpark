package com.ezpark.io.shared.infrastructure;

import com.ezpark.io.shared.event.DomainEvent;
import com.ezpark.io.shared.event.EventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KafkaEventPublisher implements EventPublisher {

    Logger LOGGER = LoggerFactory.getLogger(KafkaEventPublisher.class);

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private Map<String, String> topicMapping;

    @Value("${kafka.topics.customer-events:customer.events.v1}")
    private String customerTopic;

    @Value("${kafka.topics.reservation-events:reservation.events.v1}")
    private String reservationTopic;

    @Value("${kafka.topics.parking-events:parking.events.v1}")
    private String parkingTopic;

    @Value("${kafka.topics.payment-events:payment.events.v1}")
    private String paymentTopic;

    public KafkaEventPublisher(ObjectMapper objectMapper,
                               KafkaTemplate<String, String> kafkaTemplate) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostConstruct
    public void init() {
        this.topicMapping = Map.ofEntries(
                Map.entry("CustomerRegisteredEvent", customerTopic),

                Map.entry("ReservationRequestedEvent", reservationTopic),
                Map.entry("ReservationConfirmedEvent", reservationTopic),
                Map.entry("ReservationCancelledEvent", reservationTopic),
                Map.entry("ReservationFailedEvent", reservationTopic),
                Map.entry("ReservationCompletedEvent", reservationTopic),
                Map.entry("CheckInCompletedEvent", reservationTopic),

                Map.entry("PaymentAuthorizationRequestedEvent", paymentTopic),
                Map.entry("PaymentAuthorizationFailedEvent", paymentTopic),
                Map.entry("PaymentAuthorizedEvent", paymentTopic),
                Map.entry("PaymentCapturedEvent", paymentTopic),
                Map.entry("PaymentRefundedEvent", paymentTopic),

                Map.entry("CheckOutCompletedEvent", parkingTopic),
                Map.entry("SpotReleasedEvent", parkingTopic),
                Map.entry("SpotMaintenanceEvent", parkingTopic),
                Map.entry("SpotReservedEvent", parkingTopic)
        );
    }
    @Override
    public void publish(DomainEvent event) {
        try {
            String jsonEvent = objectMapper.writeValueAsString(event);
            String topic = determineTopic(event);

            kafkaTemplate.send(topic,event.partitionKey(), jsonEvent);
            LOGGER.info("Published to Kafka - Topic: {}, Event: {}", topic, event.getClass().getSimpleName());
        } catch ( JsonProcessingException e) {
            LOGGER.error("Failed to serialize  event", e);
        }
    }

    @Override
    public void publish(DomainEvent... events) {
        for (DomainEvent event : events) {
            publish(event);
        }
    }

    private String determineTopic(DomainEvent event) {
        // Use class name instead of Class object
        String eventName = event.getClass().getSimpleName();
        String topic = topicMapping.get(eventName);

        if (topic == null) {
            throw new IllegalArgumentException("No topic mapping for event: " + eventName);
        }
        return topic;
    }
}
