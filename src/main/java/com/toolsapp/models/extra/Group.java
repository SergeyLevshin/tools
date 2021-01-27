package com.toolsapp.models.extra;

public enum Group {
    MILLING("Фрезерный"),
    LATHE("Токарный"),
    UTILITY("Вспомогательный");

    private final String name;

    Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
