package com.macias34.restaurant;

import com.macias34.restaurant.availability.TimeSlot;
import com.macias34.restaurant.restaurant.domain.CannotConfirmReservation;
import com.macias34.restaurant.restaurant.domain.Reservation;
import com.macias34.restaurant.restaurant.domain.ReservationConfirmed;
import com.macias34.restaurant.restaurant.domain.TableId;

import static com.macias34.restaurant.DateFixtures.createDateTime;
import static com.macias34.restaurant.DomainEventAssertion.assertEvent;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ReservationTests {

    @Test
    public void shouldConfirmTable() {
        // Given
        Reservation reservation = new Reservation(new TimeSlot(createDateTime(10, 0), createDateTime(12, 0)),
                TableId.generate());

        // When
        reservation.confirm();

        // Then
        var event = reservation.getUncommitedEvents().getFirst();
        assertEvent(event, new ReservationConfirmed(reservation.getId()));
        assertTrue(reservation.isConfirmed());
    }

    @Test
    public void shouldNotConfirmTableIfReservationIsAlreadyConfirmed() {

        // Given
        Reservation reservation = new Reservation(new TimeSlot(createDateTime(10, 0), createDateTime(12, 0)),
                TableId.generate());
        reservation.confirm();

        // When & Then
        assertThatThrownBy(() -> reservation.confirm()).isInstanceOf(CannotConfirmReservation.class);
    }

}
