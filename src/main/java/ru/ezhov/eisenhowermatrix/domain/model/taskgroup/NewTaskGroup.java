package ru.ezhov.eisenhowermatrix.domain.model.taskgroup;

import java.awt.*;

public class NewTaskGroup {
    private String name;
    private Color color;

    public NewTaskGroup(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String name() {
        return name;
    }

    public Color color() {
        return color;
    }
}
