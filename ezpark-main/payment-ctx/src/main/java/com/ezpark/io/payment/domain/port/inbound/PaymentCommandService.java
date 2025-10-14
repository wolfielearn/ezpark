package com.ezpark.io.payment.domain.port.inbound;


import com.ezpark.io.shared.kernel.PaymentAuthorizationId;
import com.ezpark.io.shared.kernel.ReservationId;
import java.math.BigDecimal;

public interface PaymentCommandService {
    PaymentAuthorizationId authorizePayment(ReservationId reservationId, BigDecimal amount, String paymentMethod);
    void capturePayment(PaymentAuthorizationId paymentAuthId);
    void expirePayment(PaymentAuthorizationId paymentAuthId);
    void refundPayment(PaymentAuthorizationId paymentAuthId);
}