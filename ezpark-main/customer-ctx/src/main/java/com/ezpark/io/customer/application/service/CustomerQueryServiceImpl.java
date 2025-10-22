package com.ezpark.io.customer.application.service;

import com.ezpark.io.customer.domain.model.Customer;
import com.ezpark.io.customer.domain.model.Email;
import com.ezpark.io.customer.domain.port.inbound.CustomerQueryService;
import com.ezpark.io.customer.domain.port.outbound.CustomerRepository;
import com.ezpark.io.shared.kernel.CustomerId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CustomerQueryServiceImpl implements CustomerQueryService{

    private final CustomerRepository customerRepository;

    public CustomerQueryServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findById(CustomerId customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(()-> new IllegalArgumentException("Customer not found"));
    }

    @Override
    public boolean existsByEmail(Email email) {
        return false;
    }
}
