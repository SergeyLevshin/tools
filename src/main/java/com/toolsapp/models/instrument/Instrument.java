package com.toolsapp.models.instrument;

import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Component
public abstract class Instrument {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private Group group;

    private boolean inUse;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public Instrument() {
    }

    public Instrument(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
