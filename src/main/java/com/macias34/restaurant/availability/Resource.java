package com.macias34.restaurant.availability;

import java.util.ArrayList;
import java.util.List;

public record Resource(List<TimeSlot> slots) {

    public boolean isAvailable(TimeSlot requestedSlot) {
        return slots.stream().noneMatch(existingSlot -> existingSlot.overlaps(requestedSlot));
    }

    public Resource addSlot(TimeSlot slot) {
        List<TimeSlot> newSlots = new ArrayList<>(slots);
        newSlots.add(slot);
        return new Resource(newSlots);
    }
}
