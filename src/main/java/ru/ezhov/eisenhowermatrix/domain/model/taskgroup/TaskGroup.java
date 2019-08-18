package ru.ezhov.eisenhowermatrix.domain.model.taskgroup;

import ru.ezhov.eisenhowermatrix.domain.model.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskGroup {
    private String name;
    private TaskGroupId taskGroupId;
    private List<Task> tasks = new ArrayList<>();

    public TaskGroup(TaskGroupId taskGroupId, String name) {
        this.name = name;
        this.taskGroupId = taskGroupId;
    }

    public String name() {
        return name;
    }

    public String id() {
        return taskGroupId.id();
    }
}
