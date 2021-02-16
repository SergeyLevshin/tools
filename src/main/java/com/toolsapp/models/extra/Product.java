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
    private Set<Accessory> accessorySet = new HashSet<>();

    @ManyToMany
    private Set<CuttingTool> cuttingToolsSet = new HashSet<>();

    @ManyToMany
    private Set<MeasuringTool> measuringToolsSet = new HashSet<>();

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

    public Set<Accessory> getAccessorySet() {
        return accessorySet;
    }

    public void setAccessorySet(Set<Accessory> accessorySet) {
        this.accessorySet = accessorySet;
    }

    public Set<CuttingTool> getCuttingToolsSet() {
        return cuttingToolsSet;
    }

    public void setCuttingToolsSet(Set<CuttingTool> cuttingToolsSet) {
        this.cuttingToolsSet = cuttingToolsSet;
    }

    public Set<MeasuringTool> getMeasuringToolsSet() {
        return measuringToolsSet;
    }

    public void setMeasuringToolsSet(Set<MeasuringTool> measuringToolsSet) {
        this.measuringToolsSet = measuringToolsSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                name.equals(product.name) &&
                accessorySet.equals(product.accessorySet) &&
                cuttingToolsSet.equals(product.cuttingToolsSet) &&
                measuringToolsSet.equals(product.measuringToolsSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, accessorySet, cuttingToolsSet, measuringToolsSet);
    }
}
