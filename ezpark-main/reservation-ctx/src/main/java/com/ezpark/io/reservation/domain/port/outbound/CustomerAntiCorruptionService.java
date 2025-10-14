package com.ezpark.io.reservation.domain.port.outbound;


import com.ezpark.io.reservation.domain.port.outbound.model.ReservationCustomerView;
import com.ezpark.io.shared.kernel.CustomerId;

public interface CustomerAntiCorruptionService {
    ReservationCustomerView getCustomerForReservation(CustomerId customerId);
    boolean canCustomerMakeReservations(CustomerId customerId);
}