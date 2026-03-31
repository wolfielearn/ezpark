package com.ezpark.io.parking.infrastructure.event.consumer;

import com.ezpark.io.parking.domain.port.inbound.ParkingEventHandler;
import com.ezpark.io.payment.infrastructure.event.consumers.PaymentEventKafkaConsumer;
import com.ezpark.io.shared.event.PaymentAuthorizedEvent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ParkingEventConsumer {
    Logger LOGGER = LoggerFactory.getLogger(PaymentEventKafkaConsumer.class);
    private final ParkingEventHandler parkingEventHandler;
    private final ObjectMapper objectMapper;

    public ParkingEventConsumer(ParkingEventHandler parkingEventHandler, ObjectMapper objectMapper) {
        this.parkingEventHandler = parkingEventHandler;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(
            topics = "${kafka.topics.payment-events}",
            groupId = "${kafka.groups.parking}")
    public void handlePaymentAuthorizedEvent(String message){

        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            String eventType = jsonNode.get("eventType").asText(null);
            if (eventType == null) {
                LOGGER.error("Missing eventType. message={}", message);
                return;
            }
            if ("PaymentAuthorizedEvent".equals(eventType)) {
                PaymentAuthorizedEvent event = objectMapper.readValue(message, PaymentAuthorizedEvent.class);
                LOGGER.info("Received ReservationRequestedEvent: {}", event.getEventId());
                parkingEventHandler.handlePaymentAuthorizedEvent( event);
            } else {
                LOGGER.error("Skipping {}.", eventType);
            }
        } catch (Exception ex) {
            LOGGER.error("Failed to process reservation requested message. Message: {}", message, ex);
            throw new RuntimeException("Failed to process reservation requested message", ex);
        }
    }
}
