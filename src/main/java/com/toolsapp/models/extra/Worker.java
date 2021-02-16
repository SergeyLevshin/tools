package com.toolsapp.models.extra;

import com.toolsapp.models.tools.Accessory;
import com.toolsapp.models.tools.CuttingTool;
import com.toolsapp.models.tools.MeasuringTool;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "worker")
public class Worker {

    @Id
    private long id;

    @NotEmpty(message = "Введите наименование")
    @Column(name = "name")
    private String name;

    @ElementCollection
    private Map<Accessory, Integer> accessories = new HashMap<>();

    @ElementCollection
    private Map<CuttingTool, Integer> cuttingTools = new HashMap<>();

    @ElementCollection
    private Map<MeasuringTool, Integer> measuringTools = new HashMap<>();

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<Accessory, Integer> getAccessories() {
        return accessories;
    }

    public void setAccessories(Map<Accessory, Integer> accessories) {
        this.accessories = accessories;
    }

    public Map<CuttingTool, Integer> getCuttingTools() {
        return cuttingTools;
    }

    public void setCuttingTools(Map<CuttingTool, Integer> cuttingTools) {
        this.cuttingTools = cuttingTools;
    }

    public Map<MeasuringTool, Integer> getMeasuringTools() {
        return measuringTools;
    }

    public void setMeasuringTools(Map<MeasuringTool, Integer> measuringTools) {
        this.measuringTools = measuringTools;
    }
}
