package com.ezpark.io.reservation.infrastructure.event;

import com.ezpark.io.reservation.domain.port.inbound.ReservationEventHandler;
import com.ezpark.io.shared.event.PaymentAuthorizedEvent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ReservationEventKafkaConsumer {
    Logger LOGGER = LoggerFactory.getLogger(ReservationEventKafkaConsumer.class);

    private final ReservationEventHandler reservationEventHandler;
    private final ObjectMapper objectMapper;

    public ReservationEventKafkaConsumer(ReservationEventHandler reservationEventHandler,
                                         ObjectMapper objectMapper) {
        this.reservationEventHandler = reservationEventHandler;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "reservation-events", groupId = "reservation-ctx-group")
    public void handlePaymentAuthorized(String message) {
        LOGGER.info("--------HANDLE PaymentAuthorizedEvent CALLED SUCCESSFULLY----------------");
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            String eventType = jsonNode.get("eventType").asText();

            if ("PaymentAuthorizedEvent".equals(eventType)){
                PaymentAuthorizedEvent event = objectMapper.readValue(message, PaymentAuthorizedEvent.class);
                LOGGER.info("Received PaymentAuthorizedEvent: {}", event.getEventId());
                reservationEventHandler.handlePaymentAuthorized(event);
            } else {
                LOGGER.debug("Skipping non-PaymentAuthorized event: {}", eventType);

            }
        } catch (Exception e) {
            LOGGER.error("Failed to process payment authorization message. Message: {}", message, e);
        }
    }

}
