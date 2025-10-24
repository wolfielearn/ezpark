package com.ezpark.io.customer.infrastructure.web.adapter;

import com.ezpark.io.customer.domain.model.*;
import com.ezpark.io.customer.domain.port.inbound.CustomerCommandService;
import com.ezpark.io.customer.domain.port.inbound.CustomerQueryService;
import com.ezpark.io.customer.infrastructure.web.dto.CustomerDto;
import com.ezpark.io.customer.infrastructure.web.dto.CustomerRequest;
import com.ezpark.io.customer.infrastructure.web.dto.VehicleDto;
import com.ezpark.io.customer.infrastructure.web.mapper.CustomerDtoMapper;
import com.ezpark.io.shared.kernel.CustomerId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(("/api/customers"))
public class CustomerController {

    public final CustomerCommandService customerCommandService;
    public final CustomerQueryService customerQueryService;
    public final CustomerDtoMapper mapper;

    public CustomerController(CustomerCommandService customerCommandService,
                              CustomerQueryService customerQueryService,
                              CustomerDtoMapper mapper) {
        this.customerCommandService = customerCommandService;
        this.customerQueryService = customerQueryService;
        this.mapper = mapper;
    }


    @PostMapping
    public String saveCustomer(@RequestBody CustomerRequest request){

        Email verifyEmail =new Email(request.email());
        CustomerId id = customerCommandService.registerCustomer(request.name(), verifyEmail);
        return "SUCCESS  id = " + id.value();
    }
    @GetMapping("/{id}")
    public CustomerDto getCustomer(@PathVariable("id") String uuid){

        CustomerId customerId = new CustomerId(UUID.fromString(uuid));
        Customer customer = customerQueryService.findById(customerId);
        return mapper.toCustomerDto(customer);
    }
    @PostMapping("/{id}/vehicles")
    public ResponseEntity<VehicleDto> addVehicle(@PathVariable("id")String id, @RequestBody VehicleRequest vehicleRequest  ){

        Vehicle vehicle = customerCommandService.addVehicle(
                CustomerId.fromString(id),
                LicensePlate.fromString(vehicleRequest.license()),
                VehicleType.valueOf(vehicleRequest.type()));

        return ResponseEntity.ok(mapper.toVehicleDto(vehicle));
    }

    @GetMapping("/{customerId}/reservation-view")
    public CustomerReservationViewResponse getReservationForCustomer(@PathVariable("customerId") String uuid){

        Customer customer = customerQueryService.findById(CustomerId.fromString(uuid));
        return new CustomerReservationViewResponse(
                customer.getEmail().value(),
                customer.getName(),
                customer.canMakeReservations(),
                customer.getVehicles().isEmpty() ? null : customer.getVehicles().get(0).getLicensePlate().value()
        );

    }
    @GetMapping("/{customerId}/can-reserve")
    public BooleanResponse canCustomerMakeReservations(@PathVariable String customerId) {
        boolean canReserve = customerCommandService.canMakeReservations(CustomerId.fromString(customerId));
        return new BooleanResponse(canReserve);
    }
    public record VehicleRequest(String license, String type){}

    // Response DTOs
    public record CustomerReservationViewResponse(
            String email, String name, boolean hasVehicles, String primaryVehicleLicensePlate
    ) {}

    public record BooleanResponse(boolean canReserve) {}

}

