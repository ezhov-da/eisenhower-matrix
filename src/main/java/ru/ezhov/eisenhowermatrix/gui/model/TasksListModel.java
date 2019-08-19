package ru.ezhov.eisenhowermatrix.gui.model;

import ru.ezhov.eisenhowermatrix.application.ApplicationServices;
import ru.ezhov.eisenhowermatrix.domain.model.events.DomainEventPublisher;
import ru.ezhov.eisenhowermatrix.domain.model.events.DomainEventSubscriber;
import ru.ezhov.eisenhowermatrix.domain.model.task.Task;
import ru.ezhov.eisenhowermatrix.domain.model.task.TaskRepository;
import ru.ezhov.eisenhowermatrix.domain.model.task.events.TaskCreated;
import ru.ezhov.eisenhowermatrix.domain.model.taskgroup.TaskGroup;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public class TasksListModel extends DefaultListModel {

    public TasksListModel() {
        DomainEventPublisher.instance().subscribe(new DomainEventSubscriber<TaskCreated>() {
            public void handleEvent(TaskCreated event) {
                Optional<Task> task = ApplicationServices.taskRepository().taskOfId(event.taskId());
                if (task.isPresent()) {
                    int indexOf = TasksListModel.this.indexOf(task);
                    if (indexOf >= 0) {
                        insertElementAt(task.get(), indexOf);
                    } else {
                        addElement(task.get());
                    }
                }
            }

            public Class<TaskCreated> subscribedToEventType() {
                return TaskCreated.class;
            }
        });
        TaskRepository taskRepository = ApplicationServices.taskRepository();
        List<Task> tasks = taskRepository.tasks();
        for (Task task : tasks) {
            addElement(task);
        }
    }
}
