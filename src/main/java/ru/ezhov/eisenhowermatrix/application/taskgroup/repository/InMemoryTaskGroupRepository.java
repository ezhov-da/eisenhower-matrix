package ru.ezhov.eisenhowermatrix.application.taskgroup.repository;

import ru.ezhov.eisenhowermatrix.application.task.repository.InMemoryTaskRepository;
import ru.ezhov.eisenhowermatrix.domain.model.taskgroup.NewTaskGroup;
import ru.ezhov.eisenhowermatrix.domain.model.taskgroup.TaskGroup;
import ru.ezhov.eisenhowermatrix.domain.model.taskgroup.TaskGroupId;
import ru.ezhov.eisenhowermatrix.domain.model.taskgroup.TaskGroupRepository;

import java.util.*;

public class InMemoryTaskGroupRepository implements TaskGroupRepository {

    private static InMemoryTaskGroupRepository inMemoryTaskGroupRepository;

    private InMemoryTaskGroupRepository() {
    }

    public static InMemoryTaskGroupRepository instance(){
        if(inMemoryTaskGroupRepository == null){
            inMemoryTaskGroupRepository = new InMemoryTaskGroupRepository();
        }
        return inMemoryTaskGroupRepository;
    }

    private Map<String, TaskGroup> tasks = new HashMap<>();

    @Override
    public String newId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public List<TaskGroup> tasks() {
        return new ArrayList<>(tasks.values());
    }

    public void add(NewTaskGroup newTaskGroup) {
        String id = newId();
        tasks.put(id, new TaskGroup(new TaskGroupId(id), newTaskGroup.name()));
    }

    @Override
    public void save(TaskGroup taskGroup) {
        tasks.put(taskGroup.id(), taskGroup);
    }

    @Override
    public Optional<TaskGroup> taskGroupOfId(TaskGroupId taskGroupId) {
        return Optional.ofNullable(tasks.get(taskGroupId.id()));
    }
}
