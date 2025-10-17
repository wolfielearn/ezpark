package com.ezpark.io.payment.domain.port.inbound;


import com.ezpark.io.payment.domain.model.Amount;
import com.ezpark.io.payment.domain.model.PaymentMethod;
import com.ezpark.io.shared.kernel.PaymentAuthorizationId;
import com.ezpark.io.shared.kernel.ReservationId;

public interface PaymentCommandService {
    PaymentAuthorizationId authorizePayment(ReservationId reservationId, Amount amount, PaymentMethod paymentMethod);
    void capturePayment(PaymentAuthorizationId paymentAuthId);
    void expirePayment(PaymentAuthorizationId paymentAuthId);
    void refundPayment(PaymentAuthorizationId paymentAuthId);
}