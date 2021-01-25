package com.toolsapp.models.instrument;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class MeasuringTool extends Instrument {

    //After this date the tool should be sent for checking.
    private Date checkDate;


}
