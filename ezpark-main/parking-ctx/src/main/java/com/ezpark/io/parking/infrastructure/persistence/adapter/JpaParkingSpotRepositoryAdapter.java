package com.ezpark.io.parking.infrastructure.persistence.adapter;

import com.ezpark.io.parking.domain.model.ParkingSpot;
import com.ezpark.io.parking.domain.model.SpotStatus;
import com.ezpark.io.parking.domain.port.outbound.ParkingSpotRepository;
import com.ezpark.io.parking.infrastructure.persistence.entities.JpaParkingSpotEntity;
import com.ezpark.io.parking.infrastructure.persistence.mapper.JpaParkingEntityModelMapper;
import com.ezpark.io.parking.infrastructure.persistence.repository.JpaParkingRepository;
import com.ezpark.io.shared.kernel.SpotId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaParkingSpotRepositoryAdapter implements ParkingSpotRepository {

    private final JpaParkingRepository jpaRepository;
    private final JpaParkingEntityModelMapper mapper;

    public JpaParkingSpotRepositoryAdapter(JpaParkingRepository jpaRepository, JpaParkingEntityModelMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ParkingSpot save(ParkingSpot spot) {
        JpaParkingSpotEntity entity = mapper.toEntity(spot);
        JpaParkingSpotEntity savedEntity = jpaRepository.save(entity);
        return  mapper.toModel(savedEntity);
    }

    @Override
    public Optional<ParkingSpot> findById(SpotId spotId) {
        String id = spotId.value();
        Optional<JpaParkingSpotEntity> jpaEntityOpt = jpaRepository.findById(id);
        if(jpaEntityOpt.isPresent()){

            JpaParkingSpotEntity jpaEntity = jpaEntityOpt.get();
            return Optional.ofNullable(mapper.toModel(jpaEntity));
        }
         return Optional.empty();
    }

    @Override
    public List<ParkingSpot> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toModel).toList();
    }
}
