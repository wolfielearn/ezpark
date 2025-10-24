package com.ezpark.io.shared.event;

import java.math.BigDecimal;
import java.time.Instant;

public class PaymentAuthorizationRequestedEvent extends DomainEvent {
    private String reservationId;
    private String customerId;
    private String spotId;
    private BigDecimal amount;
    private Instant startTime;
    private Instant endTime;
    public PaymentAuthorizationRequestedEvent(){}
    public PaymentAuthorizationRequestedEvent(String reservationId, String customerId, String spotId,
                                              BigDecimal amount, Instant startTime, Instant endTime) {
        this.reservationId = reservationId;
        this.customerId = customerId;
        this.spotId = spotId;
        this.amount = amount;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getReservationId() {return reservationId;    }
    public String getCustomerId() {return customerId;}
    public String getSpotId() {return spotId;}
    public BigDecimal getAmount() {return amount;}
    public Instant getStartTime() {return startTime;}
    public Instant getEndTime() {return endTime;}
}
