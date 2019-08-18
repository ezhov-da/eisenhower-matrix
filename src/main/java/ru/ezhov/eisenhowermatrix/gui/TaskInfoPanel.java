package ru.ezhov.eisenhowermatrix.gui;

import ru.ezhov.eisenhowermatrix.application.ApplicationServices;
import ru.ezhov.eisenhowermatrix.domain.model.task.EditedTask;
import ru.ezhov.eisenhowermatrix.domain.model.task.Task;

import javax.swing.*;
import java.awt.*;

public class TaskInfoPanel extends JPanel {
    private Task task;
    private JTextField textFieldName = new JTextField();
    private JTextPane textPanelDescription = new JTextPane();
    private JButton buttonSaveEdit = new JButton("Сохранить");

    public TaskInfoPanel(Task task) {
        this.task = task;

        textFieldName.setText(task.name());
        textPanelDescription.setText(task.description());

        setLayout(new BorderLayout());
        add(textFieldName, BorderLayout.NORTH);
        add(new JScrollPane(textPanelDescription), BorderLayout.CENTER);
        add(buttonSaveEdit, BorderLayout.SOUTH);

        buttonSaveEdit.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            task.edit(new EditedTask(
                            textFieldName.getText(),
                            textPanelDescription.getText(),
                            task.rating()
                    ),
                    ApplicationServices.taskRepository()
            );
        }));
    }
}
