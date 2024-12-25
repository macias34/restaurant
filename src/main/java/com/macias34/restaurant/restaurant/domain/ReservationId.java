package com.macias34.restaurant.restaurant.domain;

import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public record ReservationId(UUID id) {

    public static ReservationId generate() {
        return new ReservationId(UUID.randomUUID());
    }
}
