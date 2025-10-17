package com.ezpark.io.customer.infrastructure.persistence;

import com.ezpark.io.customer.domain.model.Customer;
import com.ezpark.io.customer.domain.port.outbound.CustomerRepository;
import com.ezpark.io.shared.kernel.CustomerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCustomerRepository extends JpaRepository<Customer, CustomerId>, CustomerRepository{

}
