package com.toolsapp.models.extra;

import com.toolsapp.models.instrument.Instrument;

import java.util.List;

public class Product {

    private String id;

    private String name;

    private List<Instrument> toolList;

    public Product(String id, String name, List<Instrument> toolList) {
        this.id = id;
        this.name = name;
        this.toolList = toolList;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Instrument> getToolList() {
        return toolList;
    }
}
