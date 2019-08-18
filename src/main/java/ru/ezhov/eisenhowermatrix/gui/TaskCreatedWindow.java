package ru.ezhov.eisenhowermatrix.gui;

import ru.ezhov.eisenhowermatrix.application.ApplicationServices;
import ru.ezhov.eisenhowermatrix.domain.model.events.DomainEventPublisher;
import ru.ezhov.eisenhowermatrix.domain.model.events.DomainEventSubscriber;
import ru.ezhov.eisenhowermatrix.domain.model.task.EditedTask;
import ru.ezhov.eisenhowermatrix.domain.model.task.NewTask;
import ru.ezhov.eisenhowermatrix.domain.model.task.Rating;
import ru.ezhov.eisenhowermatrix.domain.model.task.Task;
import ru.ezhov.eisenhowermatrix.domain.model.task.events.TaskCreated;

import javax.swing.*;
import java.awt.*;

public class TaskCreatedWindow extends JDialog {
    private TaskCreatedPanel taskCreatedPanel = new TaskCreatedPanel();

    public TaskCreatedWindow() {
        DomainEventPublisher.instance().subscribe(new DomainEventSubscriber<TaskCreated>() {
            @Override
            public void handleEvent(TaskCreated event) {
                SwingUtilities.invokeLater(() -> {
                    if (TaskCreatedWindow.this.isVisible()) {
                        TaskCreatedWindow.this.setVisible(false);
                        TaskCreatedWindow.this.dispose();
                    }
                });
            }

            @Override
            public Class<TaskCreated> subscribedToEventType() {
                return TaskCreated.class;
            }
        });

        this.setLayout(new BorderLayout());
        this.setSize(500, 400);
        this.add(taskCreatedPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
