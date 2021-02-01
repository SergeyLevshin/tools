package com.toolsapp.models.instrument;

import com.toolsapp.models.extra.Group;
import com.toolsapp.models.extra.Producer;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class CuttingTool {

    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    @Column(name = "name")
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

    @Min(value = 1)
    @Column(name = "quantity")
    private int quantity;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CuttingTool)) return false;
        CuttingTool that = (CuttingTool) o;
        return id == that.id && quantity == that.quantity && name.equals(that.name) && group == that.group && producer == that.producer && price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, group, producer, price, quantity);
    }
}
