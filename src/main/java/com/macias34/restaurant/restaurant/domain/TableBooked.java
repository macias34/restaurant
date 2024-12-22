package com.macias34.restaurant.restaurant.domain;

import java.time.Instant;
import java.util.UUID;

import com.macias34.restaurant.common.DomainEvent;

public record TableBooked(TableId tableId, UUID eventId, Instant occurredAt) implements DomainEvent {

    public TableBooked(TableId tableId) {
        this(tableId, UUID.randomUUID(), Instant.now());
    }
}
