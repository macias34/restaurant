package com.macias34.restaurant.restaurant.domain;

public class TableIsUnavailable extends RuntimeException {
    public TableIsUnavailable() {
        super("Table is unavailable");
    }
}
