package com.ezpark.io.payment.infrastructure.event.consumers;

import com.ezpark.io.payment.domain.port.inbound.PaymentEventHandler;
import com.ezpark.io.shared.event.PaymentAuthorizationRequestedEvent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

    @KafkaListener(topics = "reservation-events", groupId = "reservation-ctx-group")
    public void handleAuthorizationRequested(String message) {

        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            String eventType = jsonNode.get("eventType").asText();
            if ("PaymentAuthorizationRequestedEvent".equals(eventType)) {
                LOGGER.info("--------HANDLE AuthorizationRequestedEvent CALLED SUCCESSFULLY----------------");
                PaymentAuthorizationRequestedEvent  event = objectMapper.readValue(
                        message, PaymentAuthorizationRequestedEvent.class);
                LOGGER.info("Received PaymentAuthorizationRequestedEvent: {}", event.getEventId());
                paymentEventHandler.handlePaymentAuthorizationRequested(event);

            } else {
                LOGGER.debug("Skipping {}.", eventType);
            }
        } catch (Exception ex) {
            LOGGER.error("Failed to process payment authorization message. Message: {}", message, ex);
        }
    }
}
