package com.toolsapp.models.tools;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class MeasuringTool extends AbstractTool{

    @Id
    private long id;

    //After this date the tool should be sent for checking.
    private Date checkDate;


}
