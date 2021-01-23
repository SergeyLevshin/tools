package com.toolsapp.models.instrument;

import javax.persistence.Entity;


@Entity
public class CuttingTool extends Instrument {


    public CuttingTool() {
    }

    public CuttingTool(int id, String name) {
        super(id, name);
    }
}
