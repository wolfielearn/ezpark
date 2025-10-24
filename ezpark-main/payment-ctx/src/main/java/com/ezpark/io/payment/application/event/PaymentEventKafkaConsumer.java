package com.ezpark.io.payment.application.event;

import com.ezpark.io.payment.domain.port.inbound.PaymentEventHandler;
import com.ezpark.io.shared.event.PaymentAuthorizationRequestedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventKafkaConsumer  {
    Logger log = LoggerFactory.getLogger(PaymentEventKafkaConsumer.class);
    private final PaymentEventHandler paymentEventHandler;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PaymentEventKafkaConsumer(PaymentEventHandler paymentEventHandler) {
        this.paymentEventHandler = paymentEventHandler;
        // 👇 EXPLICITLY register the JavaTimeModule => to avoid problem in Instant conversion
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        log.info("ObjectMapper configured with JavaTimeModule");
    }

    @KafkaListener(topics = "reservation-events", groupId = "reservation-ctx-group")
    public void handleReservationRequested(String message) {
        log.info("--------HANDLE RESERVATIONREQUESTEDEVENT CALLED SUCCESSFULLY----------------");
        try {
            PaymentAuthorizationRequestedEvent event = objectMapper.readValue(
                    message, PaymentAuthorizationRequestedEvent.class);

            log.info("Received PaymentAuthorizationRequestedEvent: {}", event.getEventId());

            paymentEventHandler.handleReservationRequested(event);

        } catch (Exception e) {
            log.error("Failed to process payment authorization message. Message: {}", message, e);
        }
    }

}
