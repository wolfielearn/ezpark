package com.ezpark.io.customer.infrastructure.persistence.adapter;

import com.ezpark.io.customer.domain.model.Customer;
import com.ezpark.io.customer.domain.model.Email;
import com.ezpark.io.customer.domain.port.outbound.CustomerRepository;
import com.ezpark.io.customer.infrastructure.persistence.entities.JpaCustomerEntity;
import com.ezpark.io.customer.infrastructure.persistence.mapper.JpaEntityModelMapper;
import com.ezpark.io.customer.infrastructure.persistence.repository.JpaCustomerRepository;
import com.ezpark.io.shared.kernel.CustomerId;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class JpaCustomerRepositoryAdapter implements CustomerRepository {

    private final JpaCustomerRepository jpaRepository;
    private final JpaEntityModelMapper mapper;

    public JpaCustomerRepositoryAdapter(JpaCustomerRepository jpaRepository, JpaEntityModelMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Customer save(Customer customer) {
        JpaCustomerEntity entity = mapper.toEntity(customer);
        // Save using Spring Data JPA
        JpaCustomerEntity savedEntity = jpaRepository.save(entity);
        return  mapper.toModel(savedEntity);
    }

    // Implements all domain repository methods using JPA
    @Override
    public Customer findById(CustomerId customerId) {
        UUID id = customerId.value();
        Optional<JpaCustomerEntity> entity = jpaRepository.findById(id);
        return  mapper.toModel(entity.get());
    }

    @Override
    public boolean existsByEmail(Email email) {
        return false;
    }

}
