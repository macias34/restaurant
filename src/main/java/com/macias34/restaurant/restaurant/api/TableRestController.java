package com.macias34.restaurant.restaurant.api;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macias34.restaurant.restaurant.application.commands.createTable.CreateTableCommand;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tables")
@RequiredArgsConstructor
public class TableRestController {
    private final CommandGateway commandGateway;

    @PostMapping("/")
    public void create(@RequestBody CreateTableCommand command) {
        commandGateway.sendAndWait(command);
    }
}
