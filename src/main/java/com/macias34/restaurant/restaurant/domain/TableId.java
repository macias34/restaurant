package com.macias34.restaurant.restaurant.domain;

import java.util.UUID;

public record TableId(UUID id) {
    public static TableId generate() {
        return new TableId(UUID.randomUUID());
    }
}
