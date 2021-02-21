package com.toolsapp.service.extra.product;

import com.toolsapp.domain.extra.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    void save(Product product);

    Product findById(long productId);

    void deleteById(long productId);

    void addTool(long productId, long toolId);

    Object getAllTools();

    Object getAllSupportTools();

    Object getAllCuttingTools();

    Object getAllMeasuringTools();
}
