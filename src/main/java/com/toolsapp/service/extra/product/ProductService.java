package com.toolsapp.service.extra.product;

import com.toolsapp.models.extra.Product;
import com.toolsapp.models.tools.AbstractTool;
import com.toolsapp.models.tools.CuttingTool;
import com.toolsapp.models.tools.MeasuringTool;
import com.toolsapp.models.tools.SupportTool;

import java.util.List;
import java.util.Optional;

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
