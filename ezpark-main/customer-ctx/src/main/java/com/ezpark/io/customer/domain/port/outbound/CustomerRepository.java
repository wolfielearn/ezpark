package com.ezpark.io.customer.domain.port.outbound;


import com.ezpark.io.customer.domain.model.Customer;
import com.ezpark.io.customer.domain.model.Email;
import com.ezpark.io.shared.kernel.CustomerId;

public interface CustomerRepository {
    Customer save(Customer customer);
    Customer findById(CustomerId customerId);
    boolean existsByEmail(Email email);
}
