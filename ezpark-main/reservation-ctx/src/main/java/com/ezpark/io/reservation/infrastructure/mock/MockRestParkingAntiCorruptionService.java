    package com.ezpark.io.reservation.infrastructure.mock;

    import com.ezpark.io.reservation.domain.port.outbound.ParkingSpotAntiCorruptionService;
    import com.ezpark.io.reservation.domain.port.outbound.model.SpotDetailsView;
    import com.ezpark.io.shared.kernel.SpotId;
    import org.springframework.boot.web.client.RestTemplateBuilder;
    import org.springframework.stereotype.Service;
    import org.springframework.web.client.RestTemplate;

    import java.time.Instant;
    import java.util.UUID;

    @Service
    public class MockRestParkingAntiCorruptionService implements ParkingSpotAntiCorruptionService{
        private final RestTemplate restTemplate;

        public MockRestParkingAntiCorruptionService(RestTemplateBuilder builder) {
            this.restTemplate = builder.build();
        }

        @Override
        public boolean isSpotAvailable(String  spotId) {
            return true;
        }

        @Override
        public SpotDetailsView getSpotDetails(String spotId) {
            SpotDetailsView details = new SpotDetailsView(new SpotId("spot001"),"local-A", "spot 001", true);
            return details;
        }

        @Override
        public boolean reserveSpot(String spotId, UUID reservationId) {
            //
            String parkingServiceUrl = "http://localhost:8080";
            String url = parkingServiceUrl + "/api/parking/{reservationId}/{spotId}/reserve";

            //assert response != null;
            return Boolean.TRUE.equals(restTemplate.getForObject(url, Boolean.class, reservationId, spotId));
        }

        @Override
        public boolean checkAvailability(String spotId, Instant startTime, Instant endTime) {
            return true;
        }

        record ReserveSpotResponse(boolean reserved){}
    }
