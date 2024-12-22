package com.macias34.restaurant.common;

import java.util.List;

import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AxonEventPublisher implements EventPublisher {
    private final EventGateway eventBus;

    public void publish(DomainEvent event) {
        eventBus.publish(event);
    }

    public void publish(List<DomainEvent> events) {
        events.forEach(this::publish);
    }
}