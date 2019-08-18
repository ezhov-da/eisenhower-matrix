package ru.ezhov.eisenhowermatrix.application;

import ru.ezhov.eisenhowermatrix.application.task.repository.InMemoryTaskRepository;
import ru.ezhov.eisenhowermatrix.application.taskgroup.repository.InMemoryTaskGroupRepository;
import ru.ezhov.eisenhowermatrix.domain.model.task.TaskRepository;
import ru.ezhov.eisenhowermatrix.domain.model.taskgroup.TaskGroupRepository;

public class ApplicationServices {

    public static TaskRepository taskRepository() {
        return InMemoryTaskRepository.instance();
    }

    public static TaskGroupRepository taskGroupRepository() {
        return InMemoryTaskGroupRepository.instance();
    }
}
