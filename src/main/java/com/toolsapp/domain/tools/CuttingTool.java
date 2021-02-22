package com.toolsapp.domain.tools;

import com.toolsapp.domain.extra.Product;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CuttingTool extends AbstractTool {

    @ManyToMany
    private Set<Product> products = new HashSet<>();

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
