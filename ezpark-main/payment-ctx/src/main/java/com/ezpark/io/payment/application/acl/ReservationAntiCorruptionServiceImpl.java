package com.ezpark.io.payment.application.acl;

import com.ezpark.io.payment.domain.port.outbound.ReservationAntiCorruptionService;
import com.ezpark.io.payment.domain.port.outbound.ReservationBillingView;
import com.ezpark.io.shared.kernel.CustomerId;
import com.ezpark.io.shared.kernel.ReservationId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;

@Service
public class ReservationAntiCorruptionServiceImpl implements ReservationAntiCorruptionService {
    private final RestTemplate restTemplate;

    @Value("${services.reservation.url:http://localhost:8080}")
    private String reservationServiceUrl;

    public ReservationAntiCorruptionServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ReservationBillingView getReservationForBilling(ReservationId reservationId) {

         try {
            // Make REST call to Reservation service
            String url = reservationServiceUrl + "/api/reservations/{reservationId}/billing-view";

            ReservationBillingResponse response = restTemplate.getForObject(
                    url, ReservationBillingResponse.class, reservationId.value()
            );

            // Convert response to domain model
            return new ReservationBillingView(
                    new ReservationId(UUID.fromString(response.reservationId())),
                    new CustomerId(UUID.fromString(response.customerId())),
                    response.customerEmail(),
                    response.estimatedAmount(),
                    response.duration()
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to get reservation details from Reservation service", e);
        }

         // Mock
        //    @Override
//    public ReservationBillingView getReservationForBilling(UUID reservationId) {
//        return new ReservationBillingView(
//                new ReservationId(reservationId),
//                new CustomerId(UUID.randomUUID()),
//                "email@mail.com",
//                BigDecimal.valueOf(20),
//                Duration.of(2,  ChronoUnit.HOURS)
//
//        );
//    }
    }

    // Response DTO for the REST call
    private record ReservationBillingResponse(
            String reservationId,
            String customerId,
            String customerEmail,
            BigDecimal estimatedAmount,
            Duration duration
    ) {}
}
