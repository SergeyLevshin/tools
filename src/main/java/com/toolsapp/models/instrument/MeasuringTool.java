package com.toolsapp.models.instrument;

import javax.persistence.Entity;
import java.time.LocalDate;


@Entity
public class MeasuringTool extends Instrument {

    //After this date the tool should be sent for checking.
    private LocalDate checkDate;

    public MeasuringTool() {
    }

    public MeasuringTool(int id, String name) {
        super(id, name);
    }

}
