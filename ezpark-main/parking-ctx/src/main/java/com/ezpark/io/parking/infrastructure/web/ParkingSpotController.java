package com.ezpark.io.parking.infrastructure.web;

import com.ezpark.io.parking.domain.port.inbound.ParkingCommandService;
import com.ezpark.io.shared.kernel.ReservationId;
import com.ezpark.io.shared.kernel.SpotId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/parking")
public class ParkingSpotController {
    private final ParkingCommandService parkingCommandService;

    public ParkingSpotController(ParkingCommandService parkingCommandService) {
        this.parkingCommandService = parkingCommandService;
    }


    @GetMapping("/{reservationId}/{spotId}/reserve")
    public boolean reserveSpot(@PathVariable("spotId") String spotId,
                               @PathVariable("reservationId") String reservationId){

        SpotId spotIdUUID = SpotId.fromString(spotId);
        ReservationId reservationIdUUID = ReservationId.from(UUID.fromString(reservationId));
        try{

            parkingCommandService.reserveSpot(spotIdUUID, reservationIdUUID);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
