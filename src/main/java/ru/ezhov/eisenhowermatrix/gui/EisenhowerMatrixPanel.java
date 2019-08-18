package ru.ezhov.eisenhowermatrix.gui;

import ru.ezhov.eisenhowermatrix.domain.model.events.DomainEventPublisher;
import ru.ezhov.eisenhowermatrix.domain.model.events.DomainEventSubscriber;
import ru.ezhov.eisenhowermatrix.domain.model.task.Rating;
import ru.ezhov.eisenhowermatrix.domain.model.task.Task;
import ru.ezhov.eisenhowermatrix.domain.model.task.events.TaskEdited;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class EisenhowerMatrixPanel extends JPanel {
    private JLabel labelNeverMind = new JLabel("<html>н<br>е<br>в<br>а<br>ж<br>н<br>о");
    private JLabel labelImportant = new JLabel("<html>в<br>а<br>ж<br>н<br>о");
    private JLabel labelUrgently = new JLabel("срочно");
    private JLabel labelDoNotRush = new JLabel("не срочно");
    private List<TaskComponent> taskComponents = new ArrayList<>();

    EisenhowerMatrixPanel() {
        setLayout(null);

        labelNeverMind.setSize(20, 150);
        labelNeverMind.setForeground(Color.BLUE);
        labelImportant.setSize(20, 150);
        labelImportant.setForeground(Color.RED);
        labelUrgently.setSize(100, 20);
        labelUrgently.setForeground(Color.RED);
        labelDoNotRush.setSize(100, 20);
        labelDoNotRush.setForeground(Color.BLUE);

        add(labelNeverMind);
        add(labelImportant);
        add(labelUrgently);
        add(labelDoNotRush);

        DomainEventPublisher.instance().subscribe(new DomainEventSubscriber<TaskEdited>() {
            @Override
            public void handleEvent(TaskEdited event) {
                repaint();
            }

            @Override
            public Class<TaskEdited> subscribedToEventType() {
                return TaskEdited.class;
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Dimension size = getSize();
        int width = size.width;
        int height = size.height;

        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.drawLine(width / 2, 0, width / 2, height);
        graphics2D.drawLine(0, height / 2, width, height / 2);

        if (this.taskComponents != null) {
            for (TaskComponent taskComponent : this.taskComponents) {
                installLocation(taskComponent);
            }
        }

        labelNeverMind.setLocation(10, height / 2 + 10);
        labelImportant.setLocation(10, 10);
        labelUrgently.setLocation(10, 10);
        labelDoNotRush.setLocation(width / 2 + 10, 10);
    }

    void setNew(List<Task> tasks) {
        this.removeAll();
        this.taskComponents = tasks.stream().map(t -> new TaskComponent(this, t)).collect(Collectors.toList());
        for (TaskComponent taskComponent : this.taskComponents) {
            installLocation(taskComponent);
            this.add(taskComponent);
        }
        this.repaint();
    }

    void add(List<Task> tasks) {
        this.taskComponents.addAll(tasks.stream().map(t -> new TaskComponent(this, t)).collect(Collectors.toList()));
        for (TaskComponent taskComponent : this.taskComponents) {
            installLocation(taskComponent);
            this.add(taskComponent);
        }
        this.repaint();
    }

    void add(Task task) {
        TaskComponent taskComponent = new TaskComponent(this, task);
        installLocation(taskComponent);
        taskComponents.add(taskComponent);
        this.add(taskComponent);
        this.repaint();
    }

    private void installLocation(TaskComponent taskComponent) {
        Dimension size = EisenhowerMatrixPanel.this.getSize();

        Task task = taskComponent.getTask();
        Rating rating = task.rating();

        taskComponent.setLocation(
                size.width * rating.urgentlyValue() / 100,
                size.height * rating.importantValue() / 100
        );
    }
}
