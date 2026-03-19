package com.ezpark.io.parking.infrastructure.event.consumer;

import com.ezpark.io.parking.domain.port.inbound.ParkingEventHandler;
import com.ezpark.io.payment.domain.port.inbound.PaymentEventHandler;
import com.ezpark.io.payment.infrastructure.event.consumers.PaymentEventKafkaConsumer;
import com.ezpark.io.shared.event.ReservationRequestedEvent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

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
            groupId = "${kafka.groups.parking}"
    )
    public void handlePaymentEvents(String message){

        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            String eventType = jsonNode.get("eventType").asText(null);
            if (eventType == null) {
                LOGGER.warn("Missing eventType. message={}", message);
                return;
            }
            if ("ReservationRequestedEvent".equals(eventType)) {
                LOGGER.info("--------HANDLE ReservationRequestedEvent CALLED SUCCESSFULLY----------------");
                ReservationRequestedEvent event = objectMapper.readValue(
                        message, ReservationRequestedEvent.class);
                LOGGER.info("Received ReservationRequestedEvent: {}", event.getEventId());
                parkingEventHandler.handleReservationRequested( event);

            } else {
                LOGGER.debug("Skipping {}.", eventType);
            }
        } catch (Exception ex) {
            LOGGER.error("Failed to process reservation requested message. Message: {}", message, ex);
            throw new RuntimeException("Failed to process reservation requested message", ex);
        }
    }
}
