package com.ezpark.io.payment.application.event;

import com.ezpark.io.payment.domain.model.Amount;
import com.ezpark.io.payment.domain.model.PaymentMethod;
import com.ezpark.io.payment.domain.port.inbound.PaymentCommandService;
import com.ezpark.io.payment.domain.port.inbound.PaymentEventHandler;
import com.ezpark.io.shared.event.ReservationRequestedEvent;
import com.ezpark.io.shared.kernel.ReservationId;
import com.ezpark.io.shared.kernel.SpotId;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentEventHandlerImpl implements PaymentEventHandler {
    private final PaymentCommandService paymentCommandService;

    public PaymentEventHandlerImpl(PaymentCommandService paymentCommandService) {
        this.paymentCommandService = paymentCommandService;
    }

    @Override
    public void handleReservationRequested(ReservationRequestedEvent event) {
        UUID uuid = event.getReservationId();
        ReservationId reservationId = ReservationId.from(uuid);
        SpotId spotId =SpotId.fromUUID(event.getSpotId());
        // TODO: replace with real pricing logic
        Amount amount = new Amount(/* BigDecimal */ java.math.BigDecimal.valueOf(5));

        paymentCommandService.authorizePayment(reservationId, spotId,  amount, PaymentMethod.CREDIT_CARD);
    }
}
