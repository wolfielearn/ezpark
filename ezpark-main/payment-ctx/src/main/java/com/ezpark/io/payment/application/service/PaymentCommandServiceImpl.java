package com.ezpark.io.payment.application.service;


import com.ezpark.io.payment.domain.model.Amount;
import com.ezpark.io.payment.domain.model.PaymentAuthorization;
import com.ezpark.io.payment.domain.model.PaymentMethod;
import com.ezpark.io.payment.domain.port.inbound.PaymentCommandService;
import com.ezpark.io.payment.domain.port.outbound.PaymentAuthorizationRepository;
import com.ezpark.io.payment.domain.port.outbound.ReservationAntiCorruptionService;
import com.ezpark.io.shared.event.*;
import com.ezpark.io.shared.kernel.PaymentAuthorizationId;
import com.ezpark.io.shared.kernel.ReservationId;
import com.ezpark.io.shared.kernel.SpotId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentCommandServiceImpl implements PaymentCommandService {

    private final PaymentAuthorizationRepository paymentAuthorizationRepository;
    private final ReservationAntiCorruptionService reservationACL;
    private final EventPublisher eventPublisher;

    public PaymentCommandServiceImpl(PaymentAuthorizationRepository paymentAuthorizationRepository,
                                     ReservationAntiCorruptionService reservationACL,
                                     EventPublisher eventPublisher) {
        this.paymentAuthorizationRepository = paymentAuthorizationRepository;
        this.reservationACL = reservationACL;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public PaymentAuthorizationId authorizePayment(ReservationId reservationId, SpotId spotId, Amount amount, PaymentMethod paymentMethod) {

       // var reservationView = reservationACL.getReservationForBilling(reservationId);
        try {
            PaymentAuthorization auth = PaymentAuthorization.create(reservationId, amount, paymentMethod);
            paymentAuthorizationRepository.save(auth);

            eventPublisher.publish(new PaymentAuthorizedEvent(
                    auth.getId().value(),
                    reservationId.value(),
                    spotId.value(),
                    amount.value()));

            return auth.getId();
        } catch (Exception ex) {
            // Handle payment failure
            eventPublisher.publish(new PaymentAuthorizationFailedEvent(
                    reservationId,
                    amount.value(),
                    ex.getMessage()
            ));
            throw new RuntimeException("Payment authorization failed", ex);
        }
    }

    @Override
    public void capturePayment(PaymentAuthorizationId paymentAuthId) {
        PaymentAuthorization auth = paymentAuthorizationRepository.findById(paymentAuthId)
                .orElseThrow(() -> new IllegalArgumentException("Payment authorization not found"));

        auth.capture();
        paymentAuthorizationRepository.save(auth);

        eventPublisher.publish(new PaymentCapturedEvent(
                paymentAuthId.value(),
                auth.getReservationId().value(),
                auth.getAmount().value()));
    }

    @Override
    public void expirePayment(PaymentAuthorizationId paymentAuthId) {
        PaymentAuthorization auth = paymentAuthorizationRepository.findById(paymentAuthId)
                .orElseThrow(() -> new IllegalArgumentException("Payment authorization not found"));

        auth.expire();
        paymentAuthorizationRepository.save(auth);

        // eventPublisher.publish(new PaymentExpiredEvent(...));
    }

    @Override
    public void refundPayment(PaymentAuthorizationId paymentAuthId) {
        PaymentAuthorization auth = paymentAuthorizationRepository.findById(paymentAuthId)
                .orElseThrow(() -> new IllegalArgumentException("Payment authorization not found"));

        auth.refund();
        paymentAuthorizationRepository.save(auth);

        eventPublisher.publish(new PaymentRefundedEvent(
                paymentAuthId,
                auth.getReservationId().value(),
                auth.getAmount().value(),
                "Customer refunded"));
    }
}