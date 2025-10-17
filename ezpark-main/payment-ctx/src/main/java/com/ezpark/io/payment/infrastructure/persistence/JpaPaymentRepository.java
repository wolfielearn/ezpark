package com.ezpark.io.payment.infrastructure.persistence;

import com.ezpark.io.payment.domain.model.PaymentAuthorization;
import com.ezpark.io.payment.domain.port.outbound.PaymentAuthorizationRepository;
import com.ezpark.io.shared.kernel.PaymentAuthorizationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPaymentRepository extends JpaRepository<PaymentAuthorization, PaymentAuthorizationId>, PaymentAuthorizationRepository {
}
