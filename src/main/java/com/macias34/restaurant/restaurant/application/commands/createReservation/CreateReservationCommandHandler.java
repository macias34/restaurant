package com.macias34.restaurant.restaurant.application.commands.createReservation;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import com.macias34.restaurant.availability.TimeSlot;
import com.macias34.restaurant.common.EventPublisher;
import com.macias34.restaurant.restaurant.domain.Reservation;
import com.macias34.restaurant.restaurant.domain.ReservationRepository;
import com.macias34.restaurant.restaurant.domain.TableId;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateReservationCommandHandler {

    private final ReservationRepository reservationRepository;
    private final EventPublisher eventPublisher;

    @CommandHandler
    public void handle(CreateReservationCommand command) {
        Reservation reservation = new Reservation(new TimeSlot(command.getStartDate(), command.getEndDate()),
                new TableId(command.getTableId()));
        reservation.confirm();

        reservationRepository.save(reservation);
        eventPublisher.publish(reservation.getUncommitedEvents());
    }
}
