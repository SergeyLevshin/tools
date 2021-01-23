package com.toolsapp.models.instrument;

import javax.persistence.Entity;


@Entity
public class Accessory extends Instrument{

    public Accessory() {
    }

    public Accessory(int id, String name) {
        super(id, name);
    }

}
