package com.macias34.restaurant.restaurant.domain;

import java.time.Instant;
import java.util.UUID;

import com.macias34.restaurant.availability.TimeSlot;
import com.macias34.restaurant.common.DomainEvent;

public record ReservationConfirmed(ReservationId reservationId, TableId tableId, TimeSlot timeSlot, Long seatsNumber,
        UUID eventId,
        Instant occurredAt)
        implements DomainEvent {

    public ReservationConfirmed(ReservationId reservationId, TableId tableId, TimeSlot timeSlot, Long seatsNumber) {
        this(reservationId, tableId, timeSlot, seatsNumber, UUID.randomUUID(), Instant.now());
    }
}
