package com.macias34.restaurant.restaurant.application.commands.createReservation;

import java.time.LocalDateTime;
import java.util.UUID;

import com.macias34.restaurant.restaurant.domain.TableId;

import lombok.NoArgsConstructor;
import lombok.Value;

public record CreateReservationCommand(UUID tableId,
                Long seatsNumber,
                LocalDateTime startDate,
                LocalDateTime endDate) {

}
