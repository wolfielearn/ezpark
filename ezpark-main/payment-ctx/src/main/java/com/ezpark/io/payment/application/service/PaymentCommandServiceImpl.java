package com.ezpark.io.payment.application.service;


import com.ezpark.io.payment.domain.model.Amount;
import com.ezpark.io.payment.domain.model.PaymentAuthorization;
import com.ezpark.io.payment.domain.model.PaymentMethod;
import com.ezpark.io.payment.domain.port.inbound.PaymentCommandService;
import com.ezpark.io.payment.domain.port.outbound.PaymentAuthorizationRepository;
import com.ezpark.io.payment.domain.port.outbound.EventPublisher;
import com.ezpark.io.payment.domain.port.outbound.ReservationAntiCorruptionService;
import com.ezpark.io.payment.domain.event.PaymentAuthorizedEvent;
import com.ezpark.io.payment.domain.event.PaymentCapturedEvent;
import com.ezpark.io.payment.domain.event.PaymentRefundedEvent;
import com.ezpark.io.shared.kernel.PaymentAuthorizationId;
import com.ezpark.io.shared.kernel.ReservationId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

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
    public PaymentAuthorizationId authorizePayment(ReservationId reservationId, Amount amount, PaymentMethod paymentMethod) {
        // Get reservation details for billing
        // to be implemented later
       // var reservationView = reservationACL.getReservationForBilling(reservationId);
        PaymentAuthorization auth = PaymentAuthorization.create(reservationId, amount, paymentMethod);

        paymentAuthorizationRepository.save(auth);

        eventPublisher.publish(new PaymentAuthorizedEvent(auth.getId(), reservationId, amount.value()));

        return auth.getId();
    }

    @Override
    public void capturePayment(PaymentAuthorizationId paymentAuthId) {
        PaymentAuthorization auth = paymentAuthorizationRepository.findById(paymentAuthId)
                .orElseThrow(() -> new IllegalArgumentException("Payment authorization not found"));

        auth.capture();
        paymentAuthorizationRepository.save(auth);

        eventPublisher.publish(new PaymentCapturedEvent(paymentAuthId, auth.getReservationId(), auth.getAmount().value()));
    }

    @Override
    public void expirePayment(PaymentAuthorizationId paymentAuthId) {
        PaymentAuthorization auth = paymentAuthorizationRepository.findById(paymentAuthId)
                .orElseThrow(() -> new IllegalArgumentException("Payment authorization not found"));

        auth.expire();
        paymentAuthorizationRepository.save(auth);
    }

    @Override
    public void refundPayment(PaymentAuthorizationId paymentAuthId) {
        PaymentAuthorization auth = paymentAuthorizationRepository.findById(paymentAuthId)
                .orElseThrow(() -> new IllegalArgumentException("Payment authorization not found"));

        auth.refund();
        paymentAuthorizationRepository.save(auth);

        eventPublisher.publish(new PaymentRefundedEvent(paymentAuthId, auth.getReservationId(), auth.getAmount().value(), "Customer request"));
    }
}