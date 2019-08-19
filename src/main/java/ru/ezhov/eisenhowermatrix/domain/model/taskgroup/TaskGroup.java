package ru.ezhov.eisenhowermatrix.domain.model.taskgroup;

import ru.ezhov.eisenhowermatrix.domain.model.events.DomainEventPublisher;
import ru.ezhov.eisenhowermatrix.domain.model.task.Task;
import ru.ezhov.eisenhowermatrix.domain.model.taskgroup.events.TaskGroupCreated;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskGroup {
    private TaskGroupId taskGroupId;
    private String name;
    private String hexColor;
    private List<Task> tasks = new ArrayList<>();

    public TaskGroup(TaskGroupId taskGroupId, String name, Color color) {
        this.taskGroupId = taskGroupId;
        this.name = name;
        this.hexColor = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    public static void create(NewTaskGroup newTaskGroup, TaskGroupRepository taskGroupRepository) {
        TaskGroupId taskGroupId = taskGroupRepository.add(newTaskGroup);
        DomainEventPublisher.instance().publish(new TaskGroupCreated(taskGroupId));
    }

    public String name() {
        return name;
    }

    public Color color() {
        return Color.decode(hexColor);
    }

    public String id() {
        return taskGroupId.id();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskGroup taskGroup = (TaskGroup) o;
        return Objects.equals(taskGroupId, taskGroup.taskGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskGroupId);
    }
}
