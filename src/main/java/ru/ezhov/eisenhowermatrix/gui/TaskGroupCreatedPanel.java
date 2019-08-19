package ru.ezhov.eisenhowermatrix.gui;

import ru.ezhov.eisenhowermatrix.application.ApplicationServices;
import ru.ezhov.eisenhowermatrix.domain.model.task.NewTask;
import ru.ezhov.eisenhowermatrix.domain.model.task.Rating;
import ru.ezhov.eisenhowermatrix.domain.model.task.Task;
import ru.ezhov.eisenhowermatrix.domain.model.taskgroup.NewTaskGroup;
import ru.ezhov.eisenhowermatrix.domain.model.taskgroup.TaskGroup;
import ru.ezhov.eisenhowermatrix.domain.model.taskgroup.TaskGroupId;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TaskGroupCreatedPanel extends JPanel {
    private JTextField textFieldName = new JTextField(40);
    private JLabel colorLabel = new JLabel("цвет группы");
    private JButton buttonCreate = new JButton("Создать");

    public TaskGroupCreatedPanel() {
        setLayout(new BorderLayout());
        add(textFieldName, BorderLayout.CENTER);
        colorLabel.setOpaque(true);
        add(colorLabel, BorderLayout.EAST);
        add(buttonCreate, BorderLayout.SOUTH);

        colorLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Color color = JColorChooser.showDialog(TaskGroupCreatedPanel.this, "Цвет группе задач", Color.GREEN);
                colorLabel.setBackground(color);


            }
        });
        buttonCreate.addActionListener(e ->
                SwingUtilities.invokeLater(() -> {
                            String name = textFieldName.getText();
                            Color color = colorLabel.getBackground();
                            TaskGroup.create(new NewTaskGroup(name, color), ApplicationServices.taskGroupRepository());
                        }
                )
        );
    }
}
