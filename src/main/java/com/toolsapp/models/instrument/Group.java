package com.toolsapp.models.instrument;

import java.io.Serializable;
import java.util.List;


public class Group implements Serializable {

    private String id;

    private List<Group> children;

    public Group() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
