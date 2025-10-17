package com.ezpark.io.customer.infrastructure.mock;

import com.ezpark.io.customer.domain.port.outbound.NotificationService;
import com.ezpark.io.shared.kernel.CustomerId;
import com.ezpark.io.shared.kernel.ReservationId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class NotificationMockService implements NotificationService {

    Logger logger = LoggerFactory.getLogger(NotificationService.class);
    @Override
    public void sendReceipt(ReservationId reservationId, BigDecimal amount) {
        // send email or SMS
        logger.info(" Sending email to customer for reservation : {}", reservationId);
    }

    @Override
    public void sendReservationFailedNotification(CustomerId customerId, String reason) {
        // Publish event for failure
        logger.info(" Sending event  Failed for customer : {}", customerId);


    }
}
