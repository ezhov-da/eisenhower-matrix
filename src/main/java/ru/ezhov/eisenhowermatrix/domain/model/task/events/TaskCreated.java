package ru.ezhov.eisenhowermatrix.domain.model.task.events;

import ru.ezhov.eisenhowermatrix.domain.model.events.DomainEvent;
import ru.ezhov.eisenhowermatrix.domain.model.task.TaskId;

public class TaskCreated extends DomainEvent {

    private TaskId taskId;

    public TaskCreated(TaskId taskId) {
        this.taskId = taskId;
    }

    @Override
    protected Class eventType() {
        return this.getClass();
    }

    public TaskId taskId() {
        return taskId;
    }
}
