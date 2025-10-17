package com.ezpark.io.reservation.domain.port.outbound;


import com.ezpark.io.reservation.domain.port.outbound.model.ReservationCustomerView;

import java.util.UUID;


public interface CustomerAntiCorruptionService {
    ReservationCustomerView getCustomerForReservation(UUID customerId);
    boolean canCustomerMakeReservations(UUID customerId);
}