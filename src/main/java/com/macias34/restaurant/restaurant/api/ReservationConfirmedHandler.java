package com.macias34.restaurant.restaurant.api;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.macias34.restaurant.restaurant.application.commands.bookTable.BookTableCommand;
import com.macias34.restaurant.restaurant.domain.ReservationConfirmed;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReservationConfirmedHandler {

    private final CommandGateway commandGateway;

    @EventHandler
    public void handle(ReservationConfirmed event) {
        commandGateway.send(new BookTableCommand(event.tableId().id(), event.timeSlot().start(),
                event.timeSlot().end(), event.seatsNumber()));
    }
}
