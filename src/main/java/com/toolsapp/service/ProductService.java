package com.toolsapp.service;

import com.toolsapp.models.extra.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    void save(Product product);
}
