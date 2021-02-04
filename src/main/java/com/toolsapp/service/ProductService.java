package com.toolsapp.service;

import com.toolsapp.models.extra.Product;
import com.toolsapp.models.instrument.CuttingTool;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    void save(Product product);

    Product findById(long id);

    void deleteById(long id);

    void updateInstrumentList(Product product, CuttingTool tool);
}
