package com.toolsapp.models.tools;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Accessory {

    @Id
    private long id;

    @NotBlank
    private String name;
}
