package com.toolsapp.models.tools;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Accessory extends AbstractTool {

    @Id
    private long id;

}
