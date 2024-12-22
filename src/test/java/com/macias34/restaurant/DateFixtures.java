package com.macias34.restaurant;

import java.time.LocalDateTime;

public class DateFixtures {
    private static final int YEAR = 2024;
    private static final int MONTH = 12;
    private static final int DAY = 12;

    public static LocalDateTime createDateTime(int hour, int minute) {
        return LocalDateTime.of(YEAR, MONTH, DAY, hour, minute);
    }
}
