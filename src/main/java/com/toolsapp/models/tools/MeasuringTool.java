package com.toolsapp.models.tools;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class MeasuringTool extends AbstractTool{

    @Id
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
}
