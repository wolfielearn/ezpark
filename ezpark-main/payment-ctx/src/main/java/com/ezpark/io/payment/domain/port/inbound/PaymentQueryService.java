package com.ezpark.io.payment.domain.port.inbound;


import com.ezpark.io.payment.domain.model.PaymentAuthorization;
import com.ezpark.io.shared.kernel.PaymentAuthorizationId;
import com.ezpark.io.shared.kernel.ReservationId;

import java.util.Optional;

public interface PaymentQueryService {
    Optional<PaymentAuthorization> findById(PaymentAuthorizationId paymentAuthId);
    boolean isPaymentAuthorized(ReservationId reservationId);
}