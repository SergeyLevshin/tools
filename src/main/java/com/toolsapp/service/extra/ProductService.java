package com.toolsapp.service.extra;

import com.toolsapp.models.extra.Product;
import com.toolsapp.models.tools.CuttingTool;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    void save(Product product);

    Product findById(long id);

    void deleteById(long id);

    void updateInstrumentList(Product product, CuttingTool tool);
}
