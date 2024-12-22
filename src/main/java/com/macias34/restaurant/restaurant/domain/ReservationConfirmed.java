package com.macias34.restaurant.restaurant.domain;

import java.time.Instant;
import java.util.UUID;

import com.macias34.restaurant.common.DomainEvent;

public record ReservationConfirmed(ReservationId reservationId, UUID eventId, Instant occurredAt)
        implements DomainEvent {

    public ReservationConfirmed(ReservationId reservationId) {
        this(reservationId, UUID.randomUUID(), Instant.now());
    }
}
