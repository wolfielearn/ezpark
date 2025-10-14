package com.ezpark.io.payment.domain.port.outbound;


import com.ezpark.io.payment.domain.model.PaymentAuthorization;
import com.ezpark.io.shared.kernel.PaymentAuthorizationId;
import java.util.Optional;

public interface PaymentAuthorizationRepository {
    PaymentAuthorization save(PaymentAuthorization paymentAuthorization);
    Optional<PaymentAuthorization> findById(PaymentAuthorizationId paymentAuthId);
    Optional<PaymentAuthorization> findByReservationId(com.ezpark.io.shared.kernel.ReservationId reservationId);
}