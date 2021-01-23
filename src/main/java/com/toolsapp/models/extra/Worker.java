package com.toolsapp.models.extra;

import com.toolsapp.models.instrument.Instrument;

import java.util.List;

public class Worker {

    private int id;

    private List<Instrument> toolList;

    public int getId() {
        return id;
    }

    public List<Instrument> getToolList() {
        return toolList;
    }
}
