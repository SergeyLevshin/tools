package com.toolsapp.domain.extra;

import com.toolsapp.domain.tools.AbstractTool;

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
    private Set<AbstractTool> toolSet = new HashSet<>();

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

    public Set< AbstractTool> getToolSet() {
        return toolSet;
    }

    public void setToolSet(Set<AbstractTool> toolSet) {
        this.toolSet = toolSet;
    }

    public void addTool(AbstractTool tool) {
        getToolSet().add(tool);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id &&
                name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
