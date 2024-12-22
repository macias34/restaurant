package com.macias34.restaurant.restaurant.application.commands.bookTable;

import org.axonframework.commandhandling.CommandHandler;

import com.macias34.restaurant.availability.TimeSlot;
import com.macias34.restaurant.common.EventPublisher;
import com.macias34.restaurant.restaurant.domain.Table;
import com.macias34.restaurant.restaurant.domain.TableId;
import com.macias34.restaurant.restaurant.domain.TableRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookTableCommandHandler {

    private final TableRepository tableRepository;
    private final EventPublisher eventPublisher;

    @CommandHandler
    public void handle(BookTableCommand command) {
        Table table = tableRepository.findById(new TableId(command.tableId())).orElseThrow();
        table.book(new TimeSlot(command.startDate(), command.endDate()), command.seatsNumber());

        tableRepository.save(table);
        eventPublisher.publish(table.getUncommitedEvents());
    }

}
