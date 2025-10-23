package com.ezpark.io.customer.infrastructure.web.dto;

import java.util.List;

public record CustomerDto(String name, String email, List<VehicleDto> vehicles) {

}
