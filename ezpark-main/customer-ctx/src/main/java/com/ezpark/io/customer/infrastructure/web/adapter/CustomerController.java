package com.ezpark.io.customer.infrastructure.web.adapter;

import com.ezpark.io.customer.domain.model.Customer;
import com.ezpark.io.customer.domain.model.Email;
import com.ezpark.io.customer.domain.port.inbound.CustomerCommandService;
import com.ezpark.io.customer.domain.port.inbound.CustomerQueryService;
import com.ezpark.io.customer.infrastructure.web.dto.CustomerDto;
import com.ezpark.io.customer.infrastructure.web.dto.CustomerRequest;
import com.ezpark.io.customer.infrastructure.web.mapper.CustomerDtoMapper;
import com.ezpark.io.shared.kernel.CustomerId;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(("/"))
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


    @PostMapping("/save")
    public String save(@RequestBody CustomerRequest request){

        Email verifyEmail =new Email(request.email());
        CustomerId id = customerCommandService.registerCustomer(request.name(), verifyEmail);
        return "SUCCESS  id = " + id.value();
    }
    @GetMapping("/customers/{id}")
    public CustomerDto get(@PathVariable("id") String uuid){

        CustomerId customerId = new CustomerId(UUID.fromString(uuid));
        Customer customer = customerQueryService.findById(customerId);
        return mapper.toDto(customer);
    }
}

