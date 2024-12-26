package com.macias34.restaurant.restaurant.domain;

import java.util.ArrayList;
import java.util.List;

import com.macias34.restaurant.availability.TimeSlot;
import com.macias34.restaurant.common.DomainEvent;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
public class Reservation {
    enum Status {
        REQUESTED,
        CONFIRMED
    }

    @Getter
    @EmbeddedId
    private ReservationId id;

    @AttributeOverride(name = "id", column = @Column(name = "table_id"))
    @Embedded
    private TableId tableId;

    @AttributeOverrides({
            @AttributeOverride(name = "start", column = @Column(name = "time_slot_start")),
            @AttributeOverride(name = "end", column = @Column(name = "time_slot_end"))
    })
    @Embedded
    private TimeSlot timeSlot;

    @Enumerated
    private Status status;

    private Long seatsNumber;

    @Getter
    @Transient
    private List<DomainEvent> uncommitedEvents = new ArrayList<>();

    public Reservation(TableId tableId, TimeSlot timeSlot, Long seatsNumber) {
        this.id = ReservationId.generate();
        this.tableId = tableId;
        this.seatsNumber = seatsNumber;
        this.timeSlot = timeSlot;
        this.status = Status.REQUESTED;
    }

    public void confirm() {
        if (status != Status.REQUESTED) {
            throw new CannotConfirmReservation();
        }

        this.status = Status.CONFIRMED;
        apply(new ReservationConfirmed(id, tableId, timeSlot, seatsNumber));
    };

    public boolean isConfirmed() {
        return status == Status.CONFIRMED;
    }

    private void apply(DomainEvent event) {
        uncommitedEvents.add(event);
    }

}
