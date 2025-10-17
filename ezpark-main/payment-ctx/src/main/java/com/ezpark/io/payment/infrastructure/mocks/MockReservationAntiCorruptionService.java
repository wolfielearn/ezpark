package com.ezpark.io.payment.infrastructure.mocks;

import com.ezpark.io.payment.domain.port.outbound.ReservationAntiCorruptionService;
import com.ezpark.io.payment.domain.port.outbound.ReservationBillingView;
import com.ezpark.io.shared.kernel.CustomerId;
import com.ezpark.io.shared.kernel.ReservationId;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class MockReservationAntiCorruptionService implements ReservationAntiCorruptionService {
    @Override
    public ReservationBillingView getReservationForBilling(UUID reservationId) {
        return new ReservationBillingView(
                new ReservationId(reservationId),
                new CustomerId(UUID.randomUUID()),
                "email@mail.com",
                BigDecimal.valueOf(20),
                Duration.of(2,  ChronoUnit.HOURS)

        );
    }
}
