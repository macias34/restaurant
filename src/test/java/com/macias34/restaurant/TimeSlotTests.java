package com.macias34.restaurant;

import org.junit.jupiter.api.Test;

import com.macias34.restaurant.availability.Resource;
import com.macias34.restaurant.availability.TimeSlot;
import static com.macias34.restaurant.DateFixtures.createDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

public class TimeSlotTests {

    @Test
    public void resourceIsNotAvailableIfGivenTimeSlotOverlaps() {
        List<TimeSlot> timeRanges = new ArrayList<>();
        timeRanges.add(new TimeSlot(createDateTime(10, 0),
                createDateTime(12, 0)));

        Resource resource = new Resource(timeRanges);
        TimeSlot overlappingTimeSlot = new TimeSlot(createDateTime(10, 0),
                createDateTime(11, 0));

        assertThat(resource.isAvailable(overlappingTimeSlot)).isFalse();
    }

    @Test
    public void resourceIsAvailableIfGivenTimeSlotDoesNotOverlap() {
        List<TimeSlot> timeRanges = new ArrayList<>();
        timeRanges.add(new TimeSlot(createDateTime(10, 0),
                createDateTime(12, 0)));

        Resource resource = new Resource(timeRanges);
        TimeSlot overlappingTimeSlot = new TimeSlot(createDateTime(12, 1),
                createDateTime(14, 0));

        assertThat(resource.isAvailable(overlappingTimeSlot)).isTrue();
    }

    @Test
    public void throwsIfTimeEndIsBeforeTimeStart() {
        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(createDateTime(10, 0),
                createDateTime(9, 0)));
    }
}
