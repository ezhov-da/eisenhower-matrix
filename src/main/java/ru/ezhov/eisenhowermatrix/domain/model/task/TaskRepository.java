package ru.ezhov.eisenhowermatrix.domain.model.task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    String newId();

    List<Task> tasks();

    TaskId add(NewTask newTask);

    void save(Task task);

    Optional<Task> taskOfId(TaskId taskId);
}
