package com.ezpark.io.customer.domain.port.outbound;

import com.ezpark.io.   shared.kernel.CustomerId;
import com.ezpark.io.   shared.kernel.ReservationId;
import java.math.BigDecimal;

public interface NotificationService {
    void sendReceipt(ReservationId reservationId, BigDecimal amount);
    void sendReservationFailedNotification(CustomerId customerId, String reason);
}