package com.toolsapp.models.extra;

import com.toolsapp.models.instrument.Accessory;
import com.toolsapp.models.instrument.CuttingTool;
import com.toolsapp.models.instrument.MeasuringTool;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "worker")
public class Worker {

    @Id
    private long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @OneToMany
    private List<Accessory> accessoryList;

    @OneToMany
    private List<CuttingTool> cuttingTools;

    @OneToMany
    private List<MeasuringTool> measuringTools;

    public long getId() {
        return id;
    }

    public List<Accessory> getAccessoryList() {
        return accessoryList;
    }

    public void setAccessoryList(List<Accessory> accessoryList) {
        this.accessoryList = accessoryList;
    }

    public List<CuttingTool> getCuttingTools() {
        return cuttingTools;
    }

    public void setCuttingTools(List<CuttingTool> cuttingTools) {
        this.cuttingTools = cuttingTools;
    }

    public List<MeasuringTool> getMeasuringTools() {
        return measuringTools;
    }

    public void setMeasuringTools(List<MeasuringTool> measuringTools) {
        this.measuringTools = measuringTools;
    }
}
