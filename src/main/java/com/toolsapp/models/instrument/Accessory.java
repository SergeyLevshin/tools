package com.toolsapp.models.instrument;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Accessory {

    @Id
    private int id;

    private String name;

    public Accessory() {
    }

    public Accessory(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
