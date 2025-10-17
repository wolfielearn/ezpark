package com.ezpark.io.customer.application.service;


import com.ezpark.io.customer.domain.event.CustomerRegisteredEvent;
import com.ezpark.io.customer.domain.model.*;
import com.ezpark.io.customer.domain.port.inbound.CustomerCommandService;
import com.ezpark.io.customer.domain.port.outbound.CustomerRepository;
import com.ezpark.io.customer.domain.port.outbound.EventPublisher;
import com.ezpark.io.shared.kernel.CustomerId;
import org.jmolecules.ddd.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CustomerCommandServiceImpl implements CustomerCommandService {

    private final CustomerRepository customerRepository;
    private final EventPublisher eventPublisher;

    public CustomerCommandServiceImpl(CustomerRepository customerRepository, EventPublisher eventPublisher) {
        this.customerRepository = customerRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public CustomerId registerCustomer(String name, Email email) {
        // Check if customer already exists with this email
        if (customerRepository.existsByEmail(email.value())) {
            throw new IllegalArgumentException("Customer with email " + email + " already exists");
        }
        Customer customer = Customer.create(name, email);
        CustomerId savedCustomerId = customerRepository.save(customer).getId();

        eventPublisher.publish(new CustomerRegisteredEvent(
                savedCustomerId.value(),
                email.value(),
                name));

        return customer.getId();
    }

    @Override
    public void addVehicle(CustomerId customerId, LicensePlate licensePlate, VehicleType vehicleType) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        customer.addVehicle(licensePlate, vehicleType);

        customerRepository.save(customer);
    }

    @Override
    public boolean canMakeReservations(CustomerId customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        return customer.canMakeReservations();
    }


}
