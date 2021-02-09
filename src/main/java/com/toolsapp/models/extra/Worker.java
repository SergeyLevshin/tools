package com.toolsapp.models.extra;

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
    private Map<Long, Integer> accessories = new HashMap<>();

    @ElementCollection
    private Map<Long, Integer> cuttingTools = new HashMap<>();

    @ElementCollection
    private Map<Long, Integer> measuringTools = new HashMap<>();

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<Long, Integer> getAccessories() {
        return accessories;
    }

    public void setAccessories(Map<Long, Integer> accessories) {
        this.accessories = accessories;
    }

    public Map<Long, Integer> getCuttingTools() {
        return cuttingTools;
    }

    public void setCuttingTools(Map<Long, Integer> cuttingTools) {
        this.cuttingTools = cuttingTools;
    }

    public Map<Long, Integer> getMeasuringTools() {
        return measuringTools;
    }

    public void setMeasuringTools(Map<Long, Integer> measuringTools) {
        this.measuringTools = measuringTools;
    }
}
