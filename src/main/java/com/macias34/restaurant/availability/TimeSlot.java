package com.macias34.restaurant.availability;

import java.time.LocalDateTime;

public record TimeSlot(LocalDateTime start, LocalDateTime end) {

    public TimeSlot {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
    }

    public boolean overlaps(TimeSlot other) {
        return !this.start().isAfter(other.end()) &&
                !other.start().isAfter(this.end());
    }

}
