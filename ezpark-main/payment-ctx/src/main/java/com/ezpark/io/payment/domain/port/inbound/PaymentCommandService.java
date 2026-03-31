package com.ezpark.io.payment.domain.port.inbound;


import com.ezpark.io.payment.domain.model.Amount;
import com.ezpark.io.payment.domain.model.PaymentMethod;
import com.ezpark.io.shared.kernel.PaymentAuthorizationId;
import com.ezpark.io.shared.kernel.ReservationId;
import com.ezpark.io.shared.kernel.SpotId;

public interface PaymentCommandService {
    PaymentAuthorizationId authorizePayment(ReservationId reservationId, SpotId spotId,Amount amount, PaymentMethod paymentMethod);
    void capturePayment(PaymentAuthorizationId paymentAuthId);
    void expirePayment(PaymentAuthorizationId paymentAuthId);
    void refundPayment(PaymentAuthorizationId paymentAuthId);
}