package com.macias34.restaurant.restaurant.application.commands.createReservation;

import java.time.LocalDateTime;

import com.macias34.restaurant.restaurant.domain.TableId;

public record CreateReservationCommand(TableId tableId, Long seatsNumber, LocalDateTime startDate,
        LocalDateTime endDate) {

}
