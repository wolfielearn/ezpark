package com.ezpark.io.customer.infrastructure.persistence.entities;

import jakarta.persistence.Embeddable;
@Embeddable
public class JpaEmailVO {
    private String value;

    protected JpaEmailVO() {}

    public JpaEmailVO(String value) {
        this.value = value; // No validation here
    }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}
