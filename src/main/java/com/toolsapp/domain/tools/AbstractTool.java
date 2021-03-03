package com.toolsapp.domain.tools;

import com.toolsapp.domain.property.ToolFunction;
import com.toolsapp.domain.property.Producer;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractTool {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tool_id")
    private long id;

    @NotEmpty(message = "Введите наименование")
    @Column(name = "name", unique = true)
    private String name;

    @OneToOne
    @JoinColumn(name = "function_id")
    private ToolFunction toolFunction;

    @OneToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @Min(value = 0, message = "Некорректная цена")
    @Column(name = "price")
    private BigDecimal price;

    @Min(value = 0, message = "Некорректное количество")
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

    public ToolFunction getToolFunction() {
        return toolFunction;
    }

    public void setToolFunction(ToolFunction toolFunction) {
        this.toolFunction = toolFunction;
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
        if (!(o instanceof AbstractTool)) return false;
        AbstractTool tool = (AbstractTool) o;
        return id == tool.id &&
                Objects.equals(name, tool.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

