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

public class TaskCreatedPanel extends JPanel {
    private JTextField textFieldName = new JTextField();
    private JTextPane textPanelDescription = new JTextPane();
    private JButton buttonCreate = new JButton("Создать");

    public TaskCreatedPanel() {
        setLayout(new BorderLayout());
        add(textFieldName, BorderLayout.NORTH);
        add(new JScrollPane(textPanelDescription), BorderLayout.CENTER);
        add(buttonCreate, BorderLayout.SOUTH);

        buttonCreate.addActionListener(e ->
                SwingUtilities.invokeLater(() ->
                        Task.create(
                                new NewTask(
                                        textFieldName.getText(),
                                        textPanelDescription.getText(),
                                        new Rating(50, 50)
                                ),
                                ApplicationServices.taskRepository()
                        )
                )
        );
    }
}
