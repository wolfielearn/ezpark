package com.ezpark.io.payment.infrastructure.event.consumers;

import com.ezpark.io.payment.domain.port.inbound.PaymentEventHandler;
import com.ezpark.io.shared.event.ReservationRequestedEvent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventKafkaConsumer  {
    Logger LOGGER = LoggerFactory.getLogger(PaymentEventKafkaConsumer.class);
    private final PaymentEventHandler paymentEventHandler;
    private final ObjectMapper objectMapper;

    public PaymentEventKafkaConsumer(PaymentEventHandler paymentEventHandler, ObjectMapper objectMapper) {
        this.paymentEventHandler = paymentEventHandler;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(
            topics = "${kafka.topics.reservation-events}",
            groupId = "${kafka.groups.payment}"
    )
    public void handleReservationRequested(String message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            String eventType = jsonNode.get("eventType").asText(null);
            if (eventType == null) {
                LOGGER.warn("Missing eventType. message={}", message);
                return;
            }
            if ("ReservationRequestedEvent".equals(eventType)) {
                ReservationRequestedEvent event = objectMapper.readValue(
                        message, ReservationRequestedEvent.class);
                LOGGER.info("Received ReservationRequestedEvent: {}", event.getEventId());
                paymentEventHandler.handleReservationRequested( event);

            } else {
                LOGGER.debug("Skipping {}.", eventType);
            }
        } catch (Exception ex) {
            LOGGER.error("Failed to process reservation requested message. Message: {}", message, ex);
            throw new RuntimeException("Failed to process reservation requested message", ex);
        }
    }
}
