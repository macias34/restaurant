package com.macias34.restaurant.restaurant.domain;

public class TableDoesNotHaveEnoughCapacity extends RuntimeException {
    public TableDoesNotHaveEnoughCapacity(Long tableCapacity, Long requestedCapacity) {
        super(String.format("Table capacity is: %s, requested: %s", tableCapacity, requestedCapacity));
    }
}
