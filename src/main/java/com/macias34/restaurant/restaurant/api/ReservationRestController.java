package com.macias34.restaurant.restaurant.api;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macias34.restaurant.restaurant.application.commands.createReservation.CreateReservationCommand;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reservations")
public class ReservationRestController {
    private final CommandGateway commandGateway;

    @PostMapping("/")
    public void create(@RequestBody CreateReservationCommand command) {
        commandGateway.sendAndWait(command);
    }

}
