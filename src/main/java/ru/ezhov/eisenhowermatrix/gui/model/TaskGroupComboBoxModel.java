package ru.ezhov.eisenhowermatrix.gui.model;

import ru.ezhov.eisenhowermatrix.application.ApplicationServices;
import ru.ezhov.eisenhowermatrix.domain.model.events.DomainEventPublisher;
import ru.ezhov.eisenhowermatrix.domain.model.events.DomainEventSubscriber;
import ru.ezhov.eisenhowermatrix.domain.model.taskgroup.TaskGroup;
import ru.ezhov.eisenhowermatrix.domain.model.taskgroup.events.TaskGroupCreated;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public class TaskGroupComboBoxModel extends DefaultComboBoxModel {

    public TaskGroupComboBoxModel() {
        DomainEventPublisher.instance().subscribe(new DomainEventSubscriber<TaskGroupCreated>() {
            @Override
            public void handleEvent(TaskGroupCreated event) {
                Optional<TaskGroup> taskGroup = ApplicationServices.taskGroupRepository().taskGroupOfId(event.taskGroupId());
                if (taskGroup.isPresent()) {
                    int indexOf = TaskGroupComboBoxModel.this.getIndexOf(taskGroup);
                    if (indexOf >= 0) {
                        insertElementAt(taskGroup.get(), indexOf);
                    } else {
                        addElement(taskGroup.get());
                    }
                }
            }

            @Override
            public Class<TaskGroupCreated> subscribedToEventType() {
                return TaskGroupCreated.class;
            }
        });

        List<TaskGroup> tasks = ApplicationServices.taskGroupRepository().tasks();
        for (TaskGroup taskGroup : tasks) {
            addElement(taskGroup);
        }
    }
}
