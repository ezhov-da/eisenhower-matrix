package ru.ezhov.eisenhowermatrix.domain.model.task;

public class Rating {
    private int importantValue;
    private int urgentlyValue;

    public Rating(int importantValue, int urgentlyValue) {
        this.setImportantValue(importantValue);
        this.setUrgentlyValue(urgentlyValue);
    }

    private void setImportantValue(int importantValue) {
        if (importantValue >= 0 && importantValue <= 100) {
            this.importantValue = importantValue;
        } else {
            throw new IllegalArgumentException("Для рейтинга допустимы значения от 0 до 100");
        }
    }

    private void setUrgentlyValue(int urgentlyValue) {
        if (urgentlyValue >= 0 && urgentlyValue <= 100) {
            this.urgentlyValue = urgentlyValue;
        } else {
            throw new IllegalArgumentException("Для рейтинга допустимы значения от 0 до 100");
        }
    }

    public int importantValue() {
        return this.importantValue;
    }

    public int urgentlyValue() {
        return this.urgentlyValue;
    }
}
