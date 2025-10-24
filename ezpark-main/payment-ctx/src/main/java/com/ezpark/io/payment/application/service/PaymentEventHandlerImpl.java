package com.ezpark.io.payment.application.service;

import com.ezpark.io.payment.domain.model.Amount;
import com.ezpark.io.payment.domain.model.PaymentMethod;
import com.ezpark.io.payment.domain.port.inbound.PaymentCommandService;
import com.ezpark.io.payment.domain.port.inbound.PaymentEventHandler;
import com.ezpark.io.shared.event.PaymentAuthorizationRequestedEvent;
import com.ezpark.io.shared.kernel.ReservationId;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentEventHandlerImpl implements PaymentEventHandler {
    private final PaymentCommandService paymentCommandService;

    public PaymentEventHandlerImpl(PaymentCommandService paymentCommandService) {
        this.paymentCommandService = paymentCommandService;
    }

    @Override
    public void handleReservationRequested(PaymentAuthorizationRequestedEvent event) {
        UUID uuid = UUID.fromString(event.getReservationId());
        ReservationId reservationId = ReservationId.from(uuid);

        Amount amount = new Amount(event.getAmount());
        paymentCommandService.authorizePayment(reservationId, amount, PaymentMethod.CREDIT_CARD);

    }
}
