package com.ezpark.io.payment.domain.port.outbound;



import com.ezpark.io.shared.kernel.CustomerId;
import com.ezpark.io.shared.kernel.ReservationId;

import java.math.BigDecimal;
import java.time.Duration;

public record ReservationBillingView(
        ReservationId reservationId,
        CustomerId customerId,
        String customerEmail,
        BigDecimal estimatedAmount,
        Duration duration
) {
    public ReservationBillingView {
        if (reservationId == null) throw new IllegalArgumentException("ReservationId cannot be null");
        if (customerId == null) throw new IllegalArgumentException("CustomerId cannot be null");
        if (customerEmail == null || customerEmail.isBlank()) throw new IllegalArgumentException("CustomerEmail cannot be empty");
        if (estimatedAmount == null || estimatedAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Estimated amount must be positive");
        }
        if (duration == null || duration.isNegative()) {
            throw new IllegalArgumentException("Duration must be positive");
        }
    }
}