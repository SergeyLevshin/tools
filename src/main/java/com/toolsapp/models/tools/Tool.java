package com.toolsapp.models.tools;

import com.toolsapp.models.extra.Group;
import com.toolsapp.models.extra.Producer;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@MappedSuperclass
public abstract class Tool {

    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "groups")
    @Enumerated(EnumType.STRING)
    private Group group;

    @Column(name = "producer")
    @Enumerated(EnumType.STRING)
    private Producer producer;

    @Min(value = 0)
    @Column(name = "price")
    private BigDecimal price;

    @Min(value = 0)
    @Column(name = "quantity")
    private int quantity;

    @Min(value = 0)
    @Column(name = "quantity_in_use")
    private int quantityInUse;

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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantityInUse() {
        return quantityInUse;
    }

    public void setQuantityInUse(int quantityInUse) {
        this.quantityInUse = quantityInUse;
    }
}

