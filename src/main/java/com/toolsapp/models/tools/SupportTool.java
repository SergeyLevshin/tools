package com.toolsapp.models.tools;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SupportTool extends AbstractTool {

    @Id
    private long id;

}
