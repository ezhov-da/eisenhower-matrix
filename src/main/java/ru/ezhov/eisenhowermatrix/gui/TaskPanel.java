package ru.ezhov.eisenhowermatrix.gui;

import ru.ezhov.eisenhowermatrix.application.ApplicationServices;
import ru.ezhov.eisenhowermatrix.domain.model.events.DomainEventPublisher;
import ru.ezhov.eisenhowermatrix.domain.model.events.DomainEventSubscriber;
import ru.ezhov.eisenhowermatrix.domain.model.task.Task;
import ru.ezhov.eisenhowermatrix.domain.model.task.events.TaskCreated;
import ru.ezhov.eisenhowermatrix.gui.model.TaskGroupComboBoxModel;
import ru.ezhov.eisenhowermatrix.gui.model.TasksListModel;
import ru.ezhov.eisenhowermatrix.gui.renderer.TaskGroupComboBoxRenderer;
import ru.ezhov.eisenhowermatrix.gui.renderer.TaskListRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Optional;

public class TaskPanel extends JPanel {

    private TaskCreatedWindow taskCreatedWindow;
    private TaskGroupCreatedWindow taskGroupCreatedWindow;
    private JToolBar toolBar = new JToolBar();
    private JList tasks = new JList();

    public TaskPanel() {
        this.setLayout(new BorderLayout());
        tasks.setModel(new TasksListModel());
        tasks.setCellRenderer(new TaskListRenderer());

        toolBar.setFloatable(false);
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
                    if (taskGroupCreatedWindow != null) {
                        taskGroupCreatedWindow.setVisible(false);
                        taskGroupCreatedWindow.dispose();
                    }

                    taskGroupCreatedWindow = new TaskGroupCreatedWindow();
                    taskGroupCreatedWindow.setVisible(true);
                });
            }
        });


        JPanel panelNorth = new JPanel(new BorderLayout());
        panelNorth.add(toolBar, BorderLayout.NORTH);

        JComboBox taskGroupComboBox = new JComboBox();
        taskGroupComboBox.setModel(new TaskGroupComboBoxModel());
        taskGroupComboBox.setRenderer(new TaskGroupComboBoxRenderer());
        panelNorth.add(taskGroupComboBox, BorderLayout.CENTER);

        add(panelNorth, BorderLayout.NORTH);
        add(new JScrollPane(tasks), BorderLayout.CENTER);
    }
}
