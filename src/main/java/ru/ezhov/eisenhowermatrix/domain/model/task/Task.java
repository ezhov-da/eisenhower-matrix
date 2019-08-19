package ru.ezhov.eisenhowermatrix.domain.model.task;

import ru.ezhov.eisenhowermatrix.domain.model.events.DomainEventPublisher;
import ru.ezhov.eisenhowermatrix.domain.model.task.events.TaskCreated;
import ru.ezhov.eisenhowermatrix.domain.model.task.events.TaskEdited;

import java.util.Objects;

public class Task {
    private TaskId taskId;
    private String name;
    private String description;
    private Rating rating;

    public Task(TaskId taskId, String name, String description, Rating rating) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public String id() {
        return taskId.id();
    }

    public Rating rating() {
        return rating;
    }

    public void newRating(Rating rating) {
        this.rating = rating;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setRating(Rating rating) {
        this.rating = rating;
    }

    public void edit(EditedTask editedTask, TaskRepository taskRepository) {
        setDescription(editedTask.description());
        setName(editedTask.name());
        setRating(editedTask.rating());
        taskRepository.save(this);
        DomainEventPublisher.instance().publish(new TaskEdited(this.taskId));
    }

    public static void create(NewTask newTask, TaskRepository taskRepository) {
        TaskId taskId = taskRepository.add(newTask);
        DomainEventPublisher.instance().publish(new TaskCreated(taskId));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(taskId, task.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId);
    }
}
