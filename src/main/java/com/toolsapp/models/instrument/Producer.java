package com.toolsapp.models.instrument;

public enum Producer {
    ISCAR("Iscar"),
    TUNGALOY("Tungaloy"),
    SANDWICK("Sandwick"),
    CANELLA("Canella"),
    KENNAMETAL("Kennametal");

    private final String name;

    Producer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
