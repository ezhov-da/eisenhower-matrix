package ru.ezhov;

import ru.ezhov.eisenhowermatrix.application.task.repository.InMemoryTaskRepository;
import ru.ezhov.eisenhowermatrix.application.taskgroup.repository.InMemoryTaskGroupRepository;
import ru.ezhov.eisenhowermatrix.gui.EisenhowerMatrixApplicationPanel;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
        {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Throwable ex) {
                //
            }

            EisenhowerMatrixApplicationPanel eisenhowerMatrixApplicationPanel = new EisenhowerMatrixApplicationPanel();

            JFrame frame = new JFrame("Матрица Эйзенхауэра");
            frame.add(eisenhowerMatrixApplicationPanel);
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
