package com.toolsapp.service.extra.extended;

import com.toolsapp.models.extra.Product;
import com.toolsapp.models.tools.MeasuringTool;
import com.toolsapp.service.extra.ProductService;
import com.toolsapp.service.tools.ToolService;

public class ProductAndMeasuringToolService
        extends AbstractProductAndToolService<MeasuringTool> {
    public ProductAndMeasuringToolService(ProductService productService, ToolService<MeasuringTool> toolService) {
        super(productService, toolService);
    }

    @Override
    protected void addToolToSet(Product product, MeasuringTool tool) {
        product.getMeasuringToolsSet().add(tool);
    }
}
