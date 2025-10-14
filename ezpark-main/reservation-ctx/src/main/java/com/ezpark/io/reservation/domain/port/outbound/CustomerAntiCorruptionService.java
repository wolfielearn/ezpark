package com.ezpark.io.reservation.domain.port.outbound;


import com.ezpark.io.shared.kernel.CustomerId;

public interface CustomerAntiCorruptionService {
    boolean canMakeReservations(CustomerId customerId);
}