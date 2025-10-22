package com.ezpark.io.customer.domain.port.inbound;


import com.ezpark.io.customer.domain.model.Customer;
import com.ezpark.io.customer.domain.model.Email;
import com.ezpark.io.shared.kernel.CustomerId;

public interface CustomerQueryService {
    Customer findById(CustomerId customerId);
    boolean existsByEmail(Email email);
}