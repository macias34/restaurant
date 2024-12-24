package com.macias34.restaurant.restaurant.domain;

import java.util.ArrayList;
import java.util.List;

import com.macias34.restaurant.availability.TimeSlot;
import com.macias34.restaurant.common.DomainEvent;

import lombok.Getter;

public class Reservation {
    enum Status {
        REQUESTED,
        CONFIRMED
    }

    @Getter
    private ReservationId id;
    private TableId tableId;
    private TimeSlot timeSlot;
    private Status status;
    @Getter
    private List<DomainEvent> uncommitedEvents = new ArrayList<>();

    public Reservation(TimeSlot timeSlot, TableId tableId) {
        this.id = ReservationId.generate();
        this.timeSlot = timeSlot;
        this.tableId = tableId;
        this.status = Status.REQUESTED;
    }

    public void confirm() {
        if (status != Status.REQUESTED) {
            throw new CannotConfirmReservation();
        }

        this.status = Status.CONFIRMED;
        apply(new ReservationConfirmed(id));
    };

    public boolean isConfirmed() {
        return status == Status.CONFIRMED;
    }

    private void apply(DomainEvent event) {
        uncommitedEvents.add(event);
    }

}
