package com.ezpark.io.customer.infrastructure.web.mapper;

import com.ezpark.io.customer.domain.model.Customer;
import com.ezpark.io.customer.domain.model.Vehicle;
import com.ezpark.io.customer.infrastructure.web.dto.CustomerDto;
import com.ezpark.io.customer.infrastructure.web.dto.VehicleDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDtoMapper {

    public CustomerDto toCustomerDto(Customer customer) {
        List<VehicleDto> vehicleDto = customer.getVehicles().stream().map(v -> new VehicleDto(v.getLicensePlate().value(), v.getType().name())).toList();
        return new CustomerDto(customer.getName(),customer.getEmail().value(), vehicleDto);
    }

    public VehicleDto toVehicleDto(Vehicle vehicle) {
        return new VehicleDto(vehicle.getLicensePlate().value(),vehicle.getType().name());
    }

    public VehicleDto toVehicleListDto(List<Vehicle> vehicles) {
        for (Vehicle v : vehicles){
            return toVehicleDto(v);
        }
        return null;
    }

}