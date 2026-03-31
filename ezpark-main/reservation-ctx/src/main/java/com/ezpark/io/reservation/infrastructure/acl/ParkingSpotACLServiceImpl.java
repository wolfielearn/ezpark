    package com.ezpark.io.reservation.infrastructure.acl;

    import com.ezpark.io.reservation.domain.port.outbound.ParkingSpotACLService;
    import com.ezpark.io.reservation.domain.port.outbound.model.SpotDetailsView;
    import com.ezpark.io.shared.kernel.SpotId;
    import org.springframework.boot.web.client.RestTemplateBuilder;
    import org.springframework.stereotype.Service;
    import org.springframework.web.client.RestTemplate;

    import java.time.Instant;
    import java.util.UUID;

    @Service
    public class ParkingSpotACLServiceImpl implements ParkingSpotACLService {
        private final RestTemplate restTemplate;

        public ParkingSpotACLServiceImpl(RestTemplateBuilder builder) {
            this.restTemplate = builder.build();
        }

        @Override
        public boolean isSpotAvailable(String  spotId) {
            return true;
        }

        @Override
        public SpotDetailsView getSpotDetails(String spotId) {
            SpotDetailsView details = new SpotDetailsView(new SpotId(UUID.fromString("spot001")),"local-A", "spot 001", "STANDARD",true);
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
        public boolean checkAvailability(UUID spotId, Instant startTime, Instant endTime) {
            return true;
        }

        record ReserveSpotResponse(boolean reserved){}
    }
