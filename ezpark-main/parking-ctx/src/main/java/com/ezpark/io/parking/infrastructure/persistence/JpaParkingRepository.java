package com.ezpark.io.parking.infrastructure.persistence;

import com.ezpark.io.parking.domain.model.ParkingSpot;
import com.ezpark.io.parking.domain.port.outbound.ParkingSpotRepository;
import com.ezpark.io.shared.kernel.SpotId;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface JpaParkingRepository extends JpaRepository<ParkingSpot, SpotId>, ParkingSpotRepository {
}
