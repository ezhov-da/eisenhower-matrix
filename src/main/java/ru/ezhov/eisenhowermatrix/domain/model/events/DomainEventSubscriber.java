package ru.ezhov.eisenhowermatrix.domain.model.events;

public abstract class DomainEventSubscriber<T> {
    public abstract void handleEvent(T event);

    public abstract Class<T> subscribedToEventType();
}
