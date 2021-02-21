package com.toolsapp.domain.tools;

import javax.persistence.*;
import java.util.Date;

@Entity
public class MeasuringTool extends AbstractTool{

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
}
