package com.ezpark.io.customer.infrastructure.persistence.mapper;

import com.ezpark.io.customer.domain.model.*;
import com.ezpark.io.customer.infrastructure.persistence.entities.JpaCustomerEntity;
import com.ezpark.io.customer.infrastructure.persistence.entities.JpaEmailVO;
import com.ezpark.io.customer.infrastructure.persistence.entities.JpaVehicleVO;
import com.ezpark.io.shared.kernel.CustomerId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class JpaEntityModelMapper {
    public Customer toModel(JpaCustomerEntity entityCustomer) {
        Email email = new Email(entityCustomer.getEmail().getValue());
        CustomerId id = new CustomerId(entityCustomer.getId());

        List<Vehicle> vehicles = entityCustomer.getVehicles().stream()
                .map(v -> {
                    LicensePlate license = new LicensePlate(v.getLicensePlate());
                    return  new Vehicle(license, VehicleType.valueOf(v.getType()));
                }).toList();

        Customer model = Customer.reconstruct(id,email, entityCustomer.getName(), vehicles);
        // Convert domain vehicles to JPA vehicles (simple data transfer)
        vehicles.forEach(model::addVehicle);
        return model;
    }

    public JpaCustomerEntity toEntity(Customer customer) {

        UUID id = customer.getId().value();
        JpaEmailVO email = new JpaEmailVO(customer.getEmail().value());
        JpaCustomerEntity entity =new JpaCustomerEntity(id,email, customer.getName());
        List<JpaVehicleVO> vehiclesVO = customer.getVehicles().stream().map(v -> new JpaVehicleVO(v.getLicensePlate().value(), v.getType().name())).toList();
        // Convert JPA vehicles to Domain vehicles

        vehiclesVO.forEach(entity::addVehicle);
        return entity;
    }
}
