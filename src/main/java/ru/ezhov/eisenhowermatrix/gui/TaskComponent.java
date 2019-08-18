package ru.ezhov.eisenhowermatrix.gui;

import ru.ezhov.eisenhowermatrix.domain.model.events.DomainEvent;
import ru.ezhov.eisenhowermatrix.domain.model.events.DomainEventPublisher;
import ru.ezhov.eisenhowermatrix.domain.model.events.DomainEventSubscriber;
import ru.ezhov.eisenhowermatrix.domain.model.task.Rating;
import ru.ezhov.eisenhowermatrix.domain.model.task.Task;
import ru.ezhov.eisenhowermatrix.domain.model.task.events.TaskEdited;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TaskComponent extends JComponent {

    private Task task;
    private Component parent;
    private JPopupMenu popupMenu = new JPopupMenu();

    public TaskComponent(Component parent, Task task) {
        this.parent = parent;
        this.task = task;

        popupMenu.add(new TaskInfoPanel(task));
        popupMenu.setPopupSize(500, 400);

        MouseMoveWindowListener mouseMoveWindowListener = new MouseMoveWindowListener(this);
        this.addMouseMotionListener(mouseMoveWindowListener);
        this.addMouseListener(mouseMoveWindowListener);

        Dimension dimension = new Dimension(17, 17);
        setSize(dimension);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);

        DomainEventPublisher.instance().subscribe(new DomainEventSubscriber<TaskEdited>() {
            @Override
            public void handleEvent(TaskEdited event) {
                if (popupMenu.isVisible() && event.taskId().id().equals(task.id())) {
                    popupMenu.setVisible(false);
                }
            }

            @Override
            public Class<TaskEdited> subscribedToEventType() {
                return TaskEdited.class;
            }
        });

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawOval(0, 0, 16, 16);
        graphics2D.setColor(Color.MAGENTA);
        graphics2D.fillOval(0, 0, 16, 16);
    }

    public Task getTask() {
        return task;
    }

    class MouseMoveWindowListener extends MouseAdapter {
        private Point diffOnScreen;
        private Component component;

        MouseMoveWindowListener(Component component) {
            this.component = component;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                SwingUtilities.invokeLater(() -> {
                    Point pressedPointLocationOnScreen = e.getLocationOnScreen();
                    int x = pressedPointLocationOnScreen.x - component.getLocationOnScreen().x;
                    int y = pressedPointLocationOnScreen.y - component.getLocationOnScreen().y;
                    diffOnScreen = new Point(x, y);
                });
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                popupMenu.show(TaskComponent.this, e.getX(), e.getY());
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            SwingUtilities.invokeLater(() -> {
                Point nowMouseLocation = e.getLocationOnScreen();
                Point point = new Point(
                        nowMouseLocation.x - diffOnScreen.x,
                        nowMouseLocation.y - diffOnScreen.y
                );
                SwingUtilities.convertPointFromScreen(point, component.getParent());

                Dimension size = parent.getSize();

                Rating newRating = new Rating(
                        point.y * 100 / size.height,
                        point.x * 100 / size.width
                );
                task.newRating(newRating);
                parent.repaint();
            });
        }
    }
}
