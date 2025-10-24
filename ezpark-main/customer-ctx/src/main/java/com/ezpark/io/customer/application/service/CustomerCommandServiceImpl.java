package com.ezpark.io.customer.application.service;


import com.ezpark.io.customer.domain.model.*;
import com.ezpark.io.customer.domain.port.inbound.CustomerCommandService;
import com.ezpark.io.customer.domain.port.outbound.CustomerRepository;
import com.ezpark.io.customer.domain.port.outbound.NotificationService;
import com.ezpark.io.shared.event.CustomerRegisteredEvent;
import com.ezpark.io.shared.event.EventPublisher;
import com.ezpark.io.shared.kernel.CustomerId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CustomerCommandServiceImpl implements CustomerCommandService {

    private final CustomerRepository customerRepository;
    private final EventPublisher eventPublisher;
    private final NotificationService notificationService;

    public CustomerCommandServiceImpl(CustomerRepository customerRepository, EventPublisher eventPublisher, NotificationService notificationService) {
        this.customerRepository = customerRepository;
        this.eventPublisher = eventPublisher;
        this.notificationService = notificationService;
    }

    @Override
    public CustomerId registerCustomer(String name, Email email) {

        if (customerRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Customer with email " + email + " already exists");
        }
        Customer customer = Customer.create(name, email);
        CustomerId savedCustomerId = customerRepository.save(customer).getId();

//       eventPublisher.publish(new CustomerRegisteredEvent(savedCustomerId.value(),email.value(),name));
        return savedCustomerId;

    }

    @Override
    public Vehicle addVehicle(CustomerId customerId, LicensePlate licensePlate, VehicleType vehicleType) {
        Customer customer = customerRepository.findById(customerId)
                        .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Vehicle vehicle = customer.addVehicle(licensePlate, vehicleType);
        customerRepository.save(customer);
        return vehicle;
    }

    @Override
    public boolean canMakeReservations(CustomerId customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        return customer.canMakeReservations();
    }
}
