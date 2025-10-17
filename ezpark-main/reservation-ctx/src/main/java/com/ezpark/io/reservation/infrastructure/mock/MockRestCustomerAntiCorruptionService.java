package com.ezpark.io.reservation.infrastructure.mock;

import com.ezpark.io.reservation.domain.port.outbound.CustomerAntiCorruptionService;
import com.ezpark.io.reservation.domain.port.outbound.model.ReservationCustomerView;
import com.ezpark.io.shared.kernel.CustomerId;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class MockRestCustomerAntiCorruptionService implements CustomerAntiCorruptionService{

    private final RestTemplate restTemplate;

    public MockRestCustomerAntiCorruptionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public ReservationCustomerView getCustomerForReservation(UUID customerUuid) {
        // TODO: Implement actual HTTP call to Customer service
        // For now, return a mock response to get things running
        CustomerId customerId = new CustomerId(customerUuid);
        return new ReservationCustomerView(
                customerId,
                "test@example.com",
                "Test Customer",
                true,
                "ABC-123"
        );
    }

    @Override
    public boolean canCustomerMakeReservations(UUID customerId) {
        return true;
    }
}
