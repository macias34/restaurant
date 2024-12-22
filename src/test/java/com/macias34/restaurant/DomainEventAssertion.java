package com.macias34.restaurant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.macias34.restaurant.common.DomainEvent;

public class DomainEventAssertion {
    public static <T extends DomainEvent> void assertEvent(T actual, T expected) {
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("eventId", "occurredAt")
                .isEqualTo(expected);
    }

    public static void assertEvents(List<DomainEvent> actual, List<DomainEvent> expected) {
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("eventId", "occurredAt")
                .isEqualTo(expected);
    }
}