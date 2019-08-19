package ru.ezhov.eisenhowermatrix.gui;

import ru.ezhov.eisenhowermatrix.application.ApplicationServices;
import ru.ezhov.eisenhowermatrix.domain.model.events.DomainEventPublisher;
import ru.ezhov.eisenhowermatrix.domain.model.events.DomainEventSubscriber;
import ru.ezhov.eisenhowermatrix.domain.model.task.Task;
import ru.ezhov.eisenhowermatrix.domain.model.task.events.TaskCreated;
import ru.ezhov.eisenhowermatrix.gui.model.TaskGroupComboBoxModel;
import ru.ezhov.eisenhowermatrix.gui.renderer.TaskGroupComboBoxRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Optional;

public class EisenhowerMatrixApplicationPanel extends JPanel {

    private EisenhowerMatrixPanel eisenhowerMatrixPanel;
    private TaskPanel taskPanel;


    public EisenhowerMatrixApplicationPanel() {
        setLayout(new BorderLayout());

        this.taskPanel = new TaskPanel();
        this.eisenhowerMatrixPanel = new EisenhowerMatrixPanel();


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

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(this.eisenhowerMatrixPanel);
        splitPane.setRightComponent(this.taskPanel);

        splitPane.setDividerLocation(700);
        splitPane.setResizeWeight(0.8);
        add(splitPane, BorderLayout.CENTER);
    }
}
