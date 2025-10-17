package com.ezpark.io.payment.domain.port.outbound;


import java.util.UUID;

public interface ReservationAntiCorruptionService {
    ReservationBillingView getReservationForBilling(UUID reservationId);
}
