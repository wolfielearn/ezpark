package com.ezpark.io.parking.application.service;


import com.ezpark.io.parking.domain.model.ParkingSpot;
import com.ezpark.io.parking.domain.model.SpotStatus;
import com.ezpark.io.parking.domain.port.inbound.ParkingQueryService;
import com.ezpark.io.parking.domain.port.outbound.ParkingSpotRepository;
import com.ezpark.io.shared.kernel.SpotId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ParkingQueryServiceImpl implements ParkingQueryService {

    private final ParkingSpotRepository parkingSpotRepository;

    public ParkingQueryServiceImpl(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    @Override
    public Optional<ParkingSpot> findById(SpotId spotId) {
        return parkingSpotRepository.findById(spotId);
    }

    @Override
    public List<ParkingSpot> findAllAvailableSpots() {
        return parkingSpotRepository.findByStatus(SpotStatus.AVAILABLE);
    }

    @Override
    public boolean isSpotAvailable(SpotId spotId) {
        return parkingSpotRepository.findById(spotId)
                .map(spot -> spot.getStatus() == SpotStatus.AVAILABLE)
                .orElse(false);
    }
}