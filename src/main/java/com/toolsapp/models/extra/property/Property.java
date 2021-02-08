package com.toolsapp.models.extra.property;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

@MappedSuperclass
public abstract class Property {

    @NotBlank
    @Column(name = "name", unique = true)
    private  String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
