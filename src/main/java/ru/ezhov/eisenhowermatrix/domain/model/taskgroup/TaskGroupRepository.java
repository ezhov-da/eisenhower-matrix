package ru.ezhov.eisenhowermatrix.domain.model.taskgroup;

import java.util.List;
import java.util.Optional;

public interface TaskGroupRepository {
    String newId();

    List<TaskGroup> tasks();

    TaskGroupId add(NewTaskGroup taskGroup);

    void save(TaskGroup taskGroup);

    Optional<TaskGroup> taskGroupOfId(TaskGroupId taskId);
}
