package ru.ezhov.eisenhowermatrix.domain.model.taskgroup.events;

import ru.ezhov.eisenhowermatrix.domain.model.events.DomainEvent;
import ru.ezhov.eisenhowermatrix.domain.model.taskgroup.TaskGroup;
import ru.ezhov.eisenhowermatrix.domain.model.taskgroup.TaskGroupId;

public class TaskGroupCreated extends DomainEvent {
    private TaskGroupId taskGroupId;

    public TaskGroupCreated(TaskGroupId taskGroupId) {
        this.taskGroupId = taskGroupId;
    }

    public TaskGroupId taskGroupId() {
        return taskGroupId;
    }

    @Override
    protected Class eventType() {
        return this.getClass();
    }
}
