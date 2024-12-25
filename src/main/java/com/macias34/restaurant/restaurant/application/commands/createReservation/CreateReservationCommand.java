package com.macias34.restaurant.restaurant.application.commands.createReservation;

import java.time.LocalDateTime;
import java.util.UUID;

import com.macias34.restaurant.restaurant.domain.TableId;

import lombok.Value;

@Value
public class CreateReservationCommand {

        private UUID tableId;
        private Long seatsNumber;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
}
