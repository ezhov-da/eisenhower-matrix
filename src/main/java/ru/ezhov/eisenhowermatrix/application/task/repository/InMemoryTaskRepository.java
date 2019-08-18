package ru.ezhov.eisenhowermatrix.application.task.repository;

import ru.ezhov.eisenhowermatrix.domain.model.task.NewTask;
import ru.ezhov.eisenhowermatrix.domain.model.task.Task;
import ru.ezhov.eisenhowermatrix.domain.model.task.TaskId;
import ru.ezhov.eisenhowermatrix.domain.model.task.TaskRepository;

import java.util.*;

public class InMemoryTaskRepository implements TaskRepository {
    private static InMemoryTaskRepository inMemoryTaskRepository;

    private InMemoryTaskRepository() {
    }

    public static InMemoryTaskRepository instance(){
        if(inMemoryTaskRepository == null){
            inMemoryTaskRepository = new InMemoryTaskRepository();
        }
        return inMemoryTaskRepository;
    }

    private Map<String, Task> tasks = new HashMap<>();

    @Override
    public String newId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public List<Task> tasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public TaskId add(NewTask newTask) {
        TaskId taskId = new TaskId(newId());
        tasks.put(taskId.id(), new Task(taskId, newTask.name(), newTask.description(), newTask.rating()));
        return taskId;
    }

    @Override
    public void save(Task task) {
        tasks.put(task.id(), task);
    }

    @Override
    public Optional<Task> taskOfId(TaskId taskId) {
        return Optional.ofNullable(tasks.get(taskId.id()));
    }
}
