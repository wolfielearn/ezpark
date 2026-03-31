package com.ezpark.io.reservation.infrastructure.event;

import com.ezpark.io.reservation.domain.port.inbound.ReservationEventHandler;
import com.ezpark.io.shared.event.SpotReservedEvent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @KafkaListener(
            topics = "${kafka.topics.parking-events}",
            groupId = "${kafka.groups.reservation}")
    public void handleSpotReservedEvent(String message) {

        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            String eventType = jsonNode.get("eventType").asText(null);
            if (eventType == null) {
                LOGGER.warn("Missing eventType. message={}", message);
                return;
            }
            if ("SpotReservedEvent".equals(eventType)) {
                SpotReservedEvent event = objectMapper.readValue(message, SpotReservedEvent.class);
                LOGGER.info("Received SpotReservedEvent: {}", event.getEventId());
                reservationEventHandler.handleSpotReserved(event);
            } else {
                LOGGER.debug("Skipping: {}", eventType);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to process reservation event message. Message: {}", message, e);
        }
    }

}
