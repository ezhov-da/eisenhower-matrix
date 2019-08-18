package ru.ezhov.eisenhowermatrix.gui;

import ru.ezhov.eisenhowermatrix.application.ApplicationServices;
import ru.ezhov.eisenhowermatrix.domain.model.events.DomainEventPublisher;
import ru.ezhov.eisenhowermatrix.domain.model.events.DomainEventSubscriber;
import ru.ezhov.eisenhowermatrix.domain.model.task.Task;
import ru.ezhov.eisenhowermatrix.domain.model.task.events.TaskCreated;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Optional;

public class EisenhowerMatrixApplicationPanel extends JPanel {
    private TaskCreatedWindow taskCreatedWindow;
    private EisenhowerMatrixPanel eisenhowerMatrixPanel;
    private JToolBar toolBar = new JToolBar();

    public EisenhowerMatrixApplicationPanel() {
        setLayout(new BorderLayout());

        this.eisenhowerMatrixPanel = new EisenhowerMatrixPanel();

        toolBar.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Создать задание");
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (taskCreatedWindow != null) {
                        taskCreatedWindow.setVisible(false);
                        taskCreatedWindow.dispose();
                    }

                    taskCreatedWindow = new TaskCreatedWindow();
                    taskCreatedWindow.setVisible(true);

                    DomainEventPublisher.instance().subscribe(new DomainEventSubscriber<TaskCreated>() {
                        @Override
                        public void handleEvent(TaskCreated event) {
                            SwingUtilities.invokeLater(() -> {
                                Optional<Task> task = ApplicationServices.taskRepository().taskOfId(event.taskId());
                                task.ifPresent(task1 -> EisenhowerMatrixApplicationPanel.this.eisenhowerMatrixPanel.add(task1));
                            });
                        }

                        @Override
                        public Class<TaskCreated> subscribedToEventType() {
                            return TaskCreated.class;
                        }
                    });
                });
            }
        });

        toolBar.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Создать группу");
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (taskCreatedWindow != null) {
                        taskCreatedWindow.setVisible(false);
                        taskCreatedWindow.dispose();
                    }

                    taskCreatedWindow = new TaskCreatedWindow();
                    taskCreatedWindow.setVisible(true);

                    DomainEventPublisher.instance().subscribe(new DomainEventSubscriber<TaskCreated>() {
                        @Override
                        public void handleEvent(TaskCreated event) {
                            SwingUtilities.invokeLater(() -> {
                                Optional<Task> task = ApplicationServices.taskRepository().taskOfId(event.taskId());
                                task.ifPresent(task1 -> EisenhowerMatrixApplicationPanel.this.eisenhowerMatrixPanel.add(task1));
                            });
                        }

                        @Override
                        public Class<TaskCreated> subscribedToEventType() {
                            return TaskCreated.class;
                        }
                    });
                });
            }
        });

        add(this.toolBar, BorderLayout.NORTH);

        add(this.eisenhowerMatrixPanel, BorderLayout.CENTER);
    }
}
