package com.ezpark.io.customer.infrastructure.web.mapper;

import com.ezpark.io.customer.domain.model.Customer;
import com.ezpark.io.customer.infrastructure.web.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoMapper {

    public CustomerDto toDto(Customer customer) {
      return new CustomerDto(customer.getName(),customer.getEmail().value(), customer.getVehicles());
}

}