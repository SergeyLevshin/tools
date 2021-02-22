package com.toolsapp.domain.extra;

import com.toolsapp.domain.tools.AbstractTool;

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


    public void decreaseToolQuantity(AbstractTool tool, int quantity) {
        int tempQuantity = getTempToolQuantity(tool);
        if (quantity < tempQuantity) {
            getTools().put(tool, tempQuantity - quantity);
        }
        else if (quantity == tempQuantity) {
            getTools().remove(tool);
        }
    }

    public void increaseToolQuantity(AbstractTool tool, int quantity) {
        int tempQuantity = getTempToolQuantity(tool);
        getTools().put(tool, tempQuantity + quantity);
    }

    private int getTempToolQuantity(AbstractTool tool) {
        return getTools().getOrDefault(tool, 0);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
