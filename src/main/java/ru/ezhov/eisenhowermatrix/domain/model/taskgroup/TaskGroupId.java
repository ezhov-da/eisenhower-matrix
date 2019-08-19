package ru.ezhov.eisenhowermatrix.domain.model.taskgroup;

import java.util.Objects;

public class TaskGroupId {
    private String id;

    public TaskGroupId(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskGroupId that = (TaskGroupId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
