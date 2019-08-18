package ru.ezhov.eisenhowermatrix.domain.model.task;

public class EditedTask {
    private String name;
    private String description;
    private Rating rating;

    public EditedTask(String name, String description, Rating rating) {
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public Rating rating() {
        return rating;
    }
}
