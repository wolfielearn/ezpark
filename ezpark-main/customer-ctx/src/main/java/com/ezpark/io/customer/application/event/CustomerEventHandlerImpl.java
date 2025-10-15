package com.ezpark.io.customer.application.event;


import com.ezpark.io.customer.domain.port.inbound.CustomerEventHandler;
import com.ezpark.io.customer.domain.port.outbound.NotificationService;

import org.springframework.stereotype.Service;

@Service
public class CustomerEventHandlerImpl implements CustomerEventHandler {

    private final NotificationService notificationService;

    public CustomerEventHandlerImpl(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void handlePaymentCaptured() {
      //  notificationService.sendReceipt(event.getReservationId(), event.getCapturedAmount());
    }

    @Override
    public void handleReservationFailed() {
        //notificationService.sendReservationFailedNotification(event.getCustomerId(), event.getFailureReason());
    }
}
