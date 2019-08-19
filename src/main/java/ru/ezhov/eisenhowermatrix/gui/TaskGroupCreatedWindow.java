package ru.ezhov.eisenhowermatrix.gui;

import ru.ezhov.eisenhowermatrix.domain.model.events.DomainEventPublisher;
import ru.ezhov.eisenhowermatrix.domain.model.events.DomainEventSubscriber;
import ru.ezhov.eisenhowermatrix.domain.model.task.events.TaskCreated;
import ru.ezhov.eisenhowermatrix.domain.model.taskgroup.events.TaskGroupCreated;

import javax.swing.*;
import java.awt.*;

public class TaskGroupCreatedWindow extends JDialog {
    private TaskGroupCreatedPanel taskGroupCreatedPanel = new TaskGroupCreatedPanel();

    public TaskGroupCreatedWindow() {
        this.setTitle("Создать группу задач");
        DomainEventPublisher.instance().subscribe(new DomainEventSubscriber<TaskGroupCreated>() {
            @Override
            public void handleEvent(TaskGroupCreated event) {
                SwingUtilities.invokeLater(() -> {
                    if (TaskGroupCreatedWindow.this.isVisible()) {
                        TaskGroupCreatedWindow.this.setVisible(false);
                        TaskGroupCreatedWindow.this.dispose();
                    }
                });
            }

            @Override
            public Class<TaskGroupCreated> subscribedToEventType() {
                return TaskGroupCreated.class;
            }
        });

        this.setLayout(new BorderLayout());
        this.add(taskGroupCreatedPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
