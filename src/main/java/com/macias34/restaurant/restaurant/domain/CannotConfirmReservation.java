package com.macias34.restaurant.restaurant.domain;

public class CannotConfirmReservation extends RuntimeException {

    public CannotConfirmReservation() {
        super("Cannot confirm reservation");
    }
}
