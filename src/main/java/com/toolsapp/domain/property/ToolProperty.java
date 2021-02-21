package com.toolsapp.domain.property;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

@MappedSuperclass
public abstract class ToolProperty {

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
