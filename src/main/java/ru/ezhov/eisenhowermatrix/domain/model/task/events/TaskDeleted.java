package ru.ezhov.eisenhowermatrix.domain.model.task.events;

import ru.ezhov.eisenhowermatrix.domain.model.events.DomainEvent;

public class TaskDeleted extends DomainEvent {
    @Override
    protected Class eventType() {
        return this.getClass();
    }
}
