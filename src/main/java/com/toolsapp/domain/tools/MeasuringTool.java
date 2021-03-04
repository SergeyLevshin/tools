package com.toolsapp.domain.tools;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class MeasuringTool extends AbstractTool{

    // Every 6 months tool should be checked.

    @Column(name = "check_date")
    private LocalDate checkDate = LocalDate.now().plusMonths(6);

    public LocalDate getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    public boolean needToCheck() {
        return checkDate.isBefore(LocalDate.now());
    }
}
