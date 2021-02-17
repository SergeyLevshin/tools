package com.toolsapp.service.extra.product.extended;

import com.toolsapp.models.extra.Product;
import com.toolsapp.models.tools.Accessory;
import com.toolsapp.service.extra.product.ProductService;
import com.toolsapp.service.tools.ToolService;
import org.springframework.stereotype.Service;

@Service
public class ProductAndAccessoryService
        extends AbstractProductAndToolService<Accessory>{

    public ProductAndAccessoryService(ProductService productService, ToolService<Accessory> toolService) {
        super(productService, toolService);
    }

    @Override
    protected void addToolToSet(Product product, Accessory tool) {
        product.getAccessorySet().add(tool);
    }

}
