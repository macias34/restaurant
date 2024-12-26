package com.macias34.restaurant.restaurant.application.commands.createTable;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import com.macias34.restaurant.restaurant.domain.Capacity;
import com.macias34.restaurant.restaurant.domain.Table;
import com.macias34.restaurant.restaurant.domain.TableDescription;
import com.macias34.restaurant.restaurant.domain.TableDescriptionRepository;
import com.macias34.restaurant.restaurant.domain.TableRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class CreateTableCommandHandler {
    private final TableRepository tableRepository;
    private final TableDescriptionRepository tableDescriptionRepository;

    @CommandHandler
    public void handle(CreateTableCommand command) {
        Table table = new Table(new Capacity(command.capacity()));
        TableDescription tableDescription = new TableDescription(table.getId(), command.description());

        tableRepository.save(table);
        tableDescriptionRepository.save(tableDescription);
    }
}
