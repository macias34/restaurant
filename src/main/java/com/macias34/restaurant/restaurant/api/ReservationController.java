package com.macias34.restaurant.restaurant.api;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macias34.restaurant.restaurant.application.commands.createReservation.CreateReservationCommand;

import lombok.RequiredArgsConstructor;

@RestController("/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private CommandBus commandBus;

    @PostMapping("/")
    public void createReservation(CreateReservationCommand command) {
        commandBus.dispatch(GenericCommandMessage.asCommandMessage(command));
    }
}
