package com.ezpark.io.payment.application.service;


import com.ezpark.io.payment.domain.model.PaymentAuthorization;
import com.ezpark.io.payment.domain.model.PaymentStatus;
import com.ezpark.io.payment.domain.port.inbound.PaymentQueryService;
import com.ezpark.io.payment.domain.port.outbound.PaymentAuthorizationRepository;
import com.ezpark.io.shared.kernel.PaymentAuthorizationId;
import com.ezpark.io.shared.kernel.ReservationId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PaymentQueryServiceImpl implements PaymentQueryService {

    private final PaymentAuthorizationRepository paymentAuthorizationRepository;

    public PaymentQueryServiceImpl(PaymentAuthorizationRepository paymentAuthorizationRepository) {
        this.paymentAuthorizationRepository = paymentAuthorizationRepository;
    }

    @Override
    public Optional<PaymentAuthorization> findById(PaymentAuthorizationId paymentAuthId) {
        return paymentAuthorizationRepository.findById(paymentAuthId);
    }

    @Override
    public boolean isPaymentAuthorized(ReservationId reservationId) {
        return paymentAuthorizationRepository.findByReservationId(reservationId)
                .map(auth -> PaymentStatus.AUTHORIZED.equals(auth.getStatus()))
                .orElse(false);
    }
}