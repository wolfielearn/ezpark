package com.ezpark.io.customer.infrastructure.web.dto;

import com.ezpark.io.customer.domain.model.Vehicle;

import java.util.List;

public record CustomerDto(String name, String email, List<Vehicle> vehicles) {

}
