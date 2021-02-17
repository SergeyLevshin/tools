package com.toolsapp.service.extra.product;

import com.toolsapp.models.extra.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();

    void save(Product product);

    Optional<Product> findById(long id);

    void deleteById(long id);
}
