package com.toolsapp.service.extra.product;

import com.toolsapp.domain.extra.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();

    Product save(Product product);

    Optional<Product> findById(long productId);

    void deleteById(long productId);

    void addTool(long productId, long toolId);

    Object getAllTools();

    Object getAllSupportTools();

    Object getAllCuttingTools();

    Object getAllMeasuringTools();
}
