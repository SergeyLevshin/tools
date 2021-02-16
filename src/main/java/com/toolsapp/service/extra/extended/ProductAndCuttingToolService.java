package com.toolsapp.service.extra.extended;

import com.toolsapp.models.extra.Product;
import com.toolsapp.models.tools.CuttingTool;
import com.toolsapp.service.extra.ProductService;
import com.toolsapp.service.tools.ToolService;

public class ProductAndCuttingToolService
        extends AbstractProductAndToolService<CuttingTool> {
    public ProductAndCuttingToolService(ProductService productService
            , ToolService<CuttingTool> toolService) {
        super(productService, toolService);
    }

    @Override
    protected void addToolToSet(Product product, CuttingTool tool) {
        product.getCuttingToolsSet().add(tool);
    }

}
