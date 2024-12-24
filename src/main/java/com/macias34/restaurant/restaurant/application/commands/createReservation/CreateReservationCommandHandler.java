package com.macias34.restaurant.restaurant.application.commands.createReservation;

import org.axonframework.commandhandling.CommandHandler;

import com.macias34.restaurant.availability.TimeSlot;
import com.macias34.restaurant.common.EventPublisher;
import com.macias34.restaurant.restaurant.domain.Reservation;
import com.macias34.restaurant.restaurant.domain.ReservationRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateReservationCommandHandler {

    private final ReservationRepository reservationRepository;
    private final EventPublisher eventPublisher;

    @CommandHandler
    public void handle(CreateReservationCommand command) {
        Reservation reservation = new Reservation(new TimeSlot(command.startDate(), command.endDate()),
                command.tableId());
        reservation.confirm();

        reservationRepository.save(reservation);
        eventPublisher.publish(reservation.getUncommitedEvents());
    }
}
