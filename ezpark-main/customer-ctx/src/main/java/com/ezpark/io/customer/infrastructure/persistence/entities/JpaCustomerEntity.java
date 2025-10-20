package com.ezpark.io.customer.infrastructure.persistence.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customer", schema = "customer")
@AttributeOverrides({
        @AttributeOverride(name = "id.value", column = @Column(name = "customer_id")),
        @AttributeOverride(name = "email.value", column = @Column(name = "email", unique = true))
})
public class JpaCustomerEntity {
    @Id
    private UUID id;

    @Embedded
    private JpaEmailVO email;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ElementCollection
    @CollectionTable(name = "customer_vehicles",
            joinColumns = @JoinColumn(name = "customer_id"),
            schema = "customer")
    private List<JpaVehicleVO> vehicles = new ArrayList<>();

    // JPA required
    protected JpaCustomerEntity() {}

    public JpaCustomerEntity(UUID id, JpaEmailVO email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    //  Simple data methods only
    public void addVehicle(JpaVehicleVO vehicle) {
        this.vehicles.add(vehicle);
    }

    //  Only getters/setters - no equals/hashCode!
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public JpaEmailVO getEmail() { return email; }
    public void setEmail(JpaEmailVO email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<JpaVehicleVO> getVehicles() { return vehicles; }
    public void setVehicles(List<JpaVehicleVO> vehicles) { this.vehicles = vehicles; }


}
