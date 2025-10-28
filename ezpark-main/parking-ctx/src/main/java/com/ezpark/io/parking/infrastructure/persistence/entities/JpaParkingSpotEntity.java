package com.ezpark.io.parking.infrastructure.persistence.entities;

import com.ezpark.io.parking.domain.model.Location;
import com.ezpark.io.parking.domain.model.SpotStatus;
import com.ezpark.io.parking.domain.model.SpotType;
import jakarta.persistence.*;

@Entity
@Table(name = "parking", schema = "parking")
public class JpaParkingSpotEntity {

    @Id
    @Column(name = "Spot_id")
    private String id;

    @Embedded
    private Location location;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private SpotType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SpotStatus status;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "current_reservation_id"))
    private JpaReservationId currentReservationId;

    protected JpaParkingSpotEntity() {}

    public JpaParkingSpotEntity(String id, Location location, SpotType type) {
        this.id = id;
        this.location = location;
        this.type = type;
        this.status = SpotStatus.AVAILABLE;
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public Location getLocation() {return location;}
    public void setLocation(Location location) {this.location = location;}
    public SpotType getType() {return type;}
    public void setType(SpotType type) {this.type = type;}
    public SpotStatus getStatus() {return status;}
    public void setStatus(SpotStatus status) {this.status = status;}
    public JpaReservationId getCurrentReservationId() {return currentReservationId;}
    public void setCurrentReservationId(JpaReservationId currentReservationId) {this.currentReservationId = currentReservationId;}
}
