package com.toolsapp.models.instrument;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class CuttingTool  {

    @Id
    private int id;

    private String name;

    public CuttingTool() {
    }

    public CuttingTool(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
