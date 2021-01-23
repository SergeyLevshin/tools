package com.toolsapp.models.instrument;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity
public class MeasuringTool {

    @Id
    private int id;

    private String name;

    //After this date the tool should be sent for checking.
    private LocalDate checkDate;

    public MeasuringTool() {
    }

    public MeasuringTool(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
