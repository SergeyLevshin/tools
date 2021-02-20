package com.toolsapp.models.extra;

import com.toolsapp.models.tools.AbstractTool;
import com.toolsapp.models.tools.SupportTool;
import com.toolsapp.models.tools.CuttingTool;
import com.toolsapp.models.tools.MeasuringTool;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "worker")
public class Worker {

    @Id
    private long id;

    @NotEmpty(message = "Введите наименование")
    @Column(name = "name")
    private String name;

    @ElementCollection
    private Map<AbstractTool, Integer> tools = new HashMap<>();

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<AbstractTool, Integer> getTools() {
        return tools;
    }

    public void setTools(Map<AbstractTool, Integer> tools) {
        this.tools = tools;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Worker)) return false;
        Worker worker = (Worker) o;
        return id == worker.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
