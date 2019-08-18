package ru.ezhov.eisenhowermatrix.domain.model.taskgroup;

public class NewTaskGroup {
    private String name;

    public NewTaskGroup(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }
}
