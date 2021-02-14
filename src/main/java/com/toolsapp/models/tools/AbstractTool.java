package com.toolsapp.models.tools;

import com.toolsapp.models.extra.property.ToolType;
import com.toolsapp.models.extra.property.Producer;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractTool {

    @Id
    @GeneratedValue
    private long id;

    @NotEmpty(message = "Введите наименование")
    @Column(name = "name", unique = true)
    private String name;

    @OneToOne
    @JoinColumn(name = "type_id")
    private ToolType toolType;

    @OneToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @Min(value = 0, message = "Некореектная цена")
    @Column(name = "price")
    private BigDecimal price;

    @Min(value = 0, message = "Некорректное количество")
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

    public ToolType getGroup() {
        return toolType;
    }

    public void setGroup(ToolType toolType) {
        this.toolType = toolType;
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

