package com.toolsapp.models.instrument;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class MeasuringTool {

    @Id
    private long id;

    //After this date the tool should be sent for checking.
    private Date checkDate;


}
