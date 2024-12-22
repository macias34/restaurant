package com.macias34.restaurant;

import com.macias34.restaurant.availability.TimeSlot;
import com.macias34.restaurant.restaurant.domain.Capacity;
import com.macias34.restaurant.restaurant.domain.Table;
import com.macias34.restaurant.restaurant.domain.TableBooked;
import com.macias34.restaurant.restaurant.domain.TableDoesNotHaveEnoughCapacity;
import com.macias34.restaurant.restaurant.domain.TableIsUnavailable;

import static com.macias34.restaurant.DomainEventAssertion.assertEvent;
import static com.macias34.restaurant.DateFixtures.createDateTime;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TableTests {

    @Test
    public void shouldBookIfTableHasEnoughCapacityAndIsAvailable() {
        // Given
        Table table = new Table(new Capacity(5L));
        TimeSlot timeSlot = new TimeSlot(createDateTime(10, 0), createDateTime(12, 0));
        Long capacity = Long.valueOf(5);

        // When
        table.book(timeSlot, capacity);

        // Then
        var event = table.getUncommitedEvents().getFirst();
        assertEvent(event, new TableBooked(table.getId()));
    }

    @Test
    public void shouldNotBookIfTableDoesNotHaveEnoughCapacity() {
        // Given
        Table table = new Table(new Capacity(5L));
        TimeSlot timeSlot = new TimeSlot(createDateTime(10, 0), createDateTime(12, 0));
        Long capacity = Long.valueOf(10);

        // When & Then
        assertThatThrownBy(() -> table.book(timeSlot, capacity)).isInstanceOf(TableDoesNotHaveEnoughCapacity.class);
    }

    @Test
    public void shouldNotBookIfTableIsUnavailable() {
        // Given
        List<TimeSlot> availability = new ArrayList<>(List.of(
                new TimeSlot(createDateTime(9, 0), createDateTime(11, 0))));
        Table table = new Table(availability, new Capacity(5L));
        TimeSlot timeSlot = new TimeSlot(createDateTime(10, 0), createDateTime(12, 0));
        Long capacity = Long.valueOf(5);

        // When & Then
        assertThatThrownBy(() -> table.book(timeSlot, capacity)).isInstanceOf(TableIsUnavailable.class);
    }

    @Test
    public void shouldNotBookIfTableWasPreviouslyBookedOnTheSameDate() {
        // Given
        Table table = new Table(new Capacity(5L));
        TimeSlot timeSlot = new TimeSlot(createDateTime(10, 0), createDateTime(12, 0));
        Long capacity = Long.valueOf(5);
        table.book(timeSlot, capacity);

        // When & Then
        assertThatThrownBy(() -> table.book(timeSlot, capacity)).isInstanceOf(TableIsUnavailable.class);
    }

}
