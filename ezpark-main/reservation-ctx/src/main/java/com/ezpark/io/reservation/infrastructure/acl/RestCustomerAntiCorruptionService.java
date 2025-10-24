package com.ezpark.io.reservation.infrastructure.acl;

import com.ezpark.io.reservation.domain.port.outbound.CustomerAntiCorruptionService;
import com.ezpark.io.reservation.domain.port.outbound.model.ReservationCustomerView;
import com.ezpark.io.shared.kernel.CustomerId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestCustomerAntiCorruptionService implements CustomerAntiCorruptionService{

    private final RestTemplate restTemplate;


    @Value("${services.customer.url:http://localhost:8080}")
    private String customerServiceUrl;

    public RestCustomerAntiCorruptionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public ReservationCustomerView getCustomerForReservation(CustomerId customerId) {
            try {
                String url = customerServiceUrl + "/api/customers/{customerId}/reservation-view";

                CustomerReservationViewResponse response = restTemplate.getForObject(
                        url, CustomerReservationViewResponse.class, customerId.value()
                );

                assert response != null;
                return new ReservationCustomerView(
                        customerId,
                        response.email(),
                        response.name(),
                        response.hasVehicles(),
                        response.primaryVehicleLicensePlate()
                );

            } catch (Exception e) {
                throw new RuntimeException("Failed to get customer details from Customer service: " + customerId, e);
            }
        }

        @Override
        public boolean canCustomerMakeReservations(CustomerId customerId) {
            try {
                String url = customerServiceUrl + "/api/customers/{customerId}/can-reserve";

                BooleanResponse response = restTemplate.getForObject(
                        url, BooleanResponse.class, customerId.value()
                );

                return response != null && response.canReserve();
            } catch (Exception e) {
                throw new RuntimeException("Failed to validate customer reservation capability: " + customerId, e);
            }
        }

        // Response DTOs
        private record CustomerReservationViewResponse(
                String email,
                String name,
                boolean hasVehicles,
                String primaryVehicleLicensePlate
        ) {}

        private record BooleanResponse(boolean canReserve) {}
}
