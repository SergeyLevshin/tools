package com.toolsapp.models.instrument;

import java.io.Serializable;
import java.util.List;


public class Group implements Serializable {

    private long id;

    private List<Group> children;

    public Group() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
