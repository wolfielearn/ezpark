package com.ezpark.io.reservation.infrastructure.web;

import com.ezpark.io.reservation.domain.port.inbound.ReservationCommandService;
import com.ezpark.io.reservation.domain.model.TimeSlot;
import com.ezpark.io.shared.kernel.CustomerId;
import com.ezpark.io.shared.kernel.ReservationId;
import com.ezpark.io.shared.kernel.SpotId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationCommandService reservationCommandService;

    public ReservationController(ReservationCommandService reservationCommandService) {
        this.reservationCommandService = reservationCommandService;
    }

    @PostMapping
    public ResponseEntity<String > makeReservation(@RequestBody CreateReservationRequest request){

        Instant startTime = Instant.parse(request.startTime());
        Instant endTime = Instant.parse(request.endTime())
                ;
        System.out.println(startTime.isAfter(endTime));
        ReservationId reservationId = reservationCommandService.createReservation(
                CustomerId.fromString(request.customerId()),
                SpotId.fromString(request.spotId()),
                new TimeSlot(startTime, endTime));
        return ResponseEntity.ok("Reservation SUCCEEDED with id : " +reservationId.value().toString());

    }

    // Request DTO
    public record CreateReservationRequest(
            String customerId,
            String spotId,
            String startTime,
            String endTime
    ) {}
}
