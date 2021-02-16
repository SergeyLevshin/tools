package com.toolsapp.models.property;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

@MappedSuperclass
public abstract class Property {

    @NotEmpty(message = "Введите наименование")
    @Column(name = "name", unique = true)
    private  String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
