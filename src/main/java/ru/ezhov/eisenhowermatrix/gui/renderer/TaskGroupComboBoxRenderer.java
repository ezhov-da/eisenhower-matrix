package ru.ezhov.eisenhowermatrix.gui.renderer;

import ru.ezhov.eisenhowermatrix.domain.model.taskgroup.TaskGroup;

import javax.swing.*;
import java.awt.*;

public class TaskGroupComboBoxRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel component = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value != null) {
            TaskGroup taskGroup = (TaskGroup) value;
            component.setBackground(taskGroup.color());
            component.setText(taskGroup.name());
        }
        return component;
    }
}
