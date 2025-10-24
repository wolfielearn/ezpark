package com.ezpark.io.payment.domain.port.outbound;


import com.ezpark.io.shared.kernel.ReservationId;

public interface ReservationAntiCorruptionService {
    ReservationBillingView getReservationForBilling(ReservationId reservationId);
}
