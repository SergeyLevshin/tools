package com.toolsapp.models.extra;

import com.toolsapp.models.tools.Accessory;
import com.toolsapp.models.tools.CuttingTool;
import com.toolsapp.models.tools.MeasuringTool;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    private long id;

    @NotEmpty(message = "Введите наименование")
    @Column(name = "name", unique = true)
    private String name;

    @ManyToMany
    private Set<Accessory> accessoryList = new HashSet<>();

    @ManyToMany
    private Set<CuttingTool> cuttingTools = new HashSet<>();

    @ManyToMany
    private Set<MeasuringTool> measuringTools = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Accessory> getAccessoryList() {
        return accessoryList;
    }

    public void setAccessoryList(Set<Accessory> accessoryList) {
        this.accessoryList = accessoryList;
    }

    public Set<CuttingTool> getCuttingTools() {
        return cuttingTools;
    }

    public void setCuttingTools(Set<CuttingTool> cuttingTools) {
        this.cuttingTools = cuttingTools;
    }

    public Set<MeasuringTool> getMeasuringTools() {
        return measuringTools;
    }

    public void setMeasuringTools(Set<MeasuringTool> measuringTools) {
        this.measuringTools = measuringTools;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                name.equals(product.name) &&
                accessoryList.equals(product.accessoryList) &&
                cuttingTools.equals(product.cuttingTools) &&
                measuringTools.equals(product.measuringTools);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, accessoryList, cuttingTools, measuringTools);
    }
}
