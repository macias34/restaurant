package com.macias34.restaurant.restaurant.domain;

public record Capacity(Long value) {
    public Capacity {
        if (value < 1) {
            throw new IllegalArgumentException("Capacity value must be at least 1");
        }
    }

    public boolean isEnough(Long requestedValue) {
        return value() >= requestedValue;
    }

}
