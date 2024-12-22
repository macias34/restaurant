package com.macias34.restaurant.restaurant.application.commands.bookTable;

import java.time.LocalDateTime;
import java.util.UUID;

public record BookTableCommand(
        UUID tableId, Long seatsNumber, LocalDateTime startDate, LocalDateTime endDate) {

}
