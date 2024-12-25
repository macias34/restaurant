package com.macias34.restaurant.restaurant.domain;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Type;

import com.macias34.restaurant.availability.Resource;
import com.macias34.restaurant.availability.TimeSlot;
import com.macias34.restaurant.common.DomainEvent;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;

@Entity
@jakarta.persistence.Table
public class Table {
    @Getter
    @EmbeddedId
    private TableId id;

    @Type(JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private Resource availability = new Resource(List.of());

    @AttributeOverride(name = "value", column = @Column(name = "capacity"))
    @Embedded
    private Capacity capacity;

    @Getter
    @Transient
    private List<DomainEvent> uncommitedEvents = new ArrayList<>();

    public Table() {
        id = TableId.generate();
        availability = new Resource(List.of());
    }

    public Table(Capacity capacity) {
        this();
        this.capacity = capacity;
    }

    public Table(List<TimeSlot> availability, Capacity capacity) {
        this(capacity);
        this.availability = new Resource(availability);
    }

    public void book(TimeSlot timeSlot, Long seatsNumber) {
        if (!capacity.isEnough(seatsNumber)) {
            throw new TableDoesNotHaveEnoughCapacity(capacity.value(), seatsNumber);
        }

        if (!availability.isAvailable(timeSlot)) {
            throw new TableIsUnavailable();
        }

        availability = availability.addSlot(timeSlot);
        apply(new TableBooked(id));
    }

    private void apply(DomainEvent event) {
        uncommitedEvents.add(event);
    }

}
