package ru.ezhov.eisenhowermatrix.gui.renderer;

import ru.ezhov.eisenhowermatrix.domain.model.task.Task;
import ru.ezhov.eisenhowermatrix.domain.model.taskgroup.TaskGroup;

import javax.swing.*;
import java.awt.*;

public class TaskListRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel component = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value != null) {
            Task task = (Task) value;
            component.setText(task.name());
        }
        return component;
    }
}
