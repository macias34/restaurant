package com.macias34.restaurant.common;

import java.util.List;

public interface EventPublisher {

    public void publish(DomainEvent event);

    public void publish(List<DomainEvent> events);

}