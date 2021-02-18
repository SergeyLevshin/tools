package com.toolsapp.service.extra.product.extended;

import com.toolsapp.models.extra.Product;
import com.toolsapp.models.tools.AbstractTool;
import com.toolsapp.models.tools.Accessory;
import com.toolsapp.models.tools.CuttingTool;
import com.toolsapp.models.tools.MeasuringTool;
import com.toolsapp.service.extra.product.ProductService;
import com.toolsapp.service.tools.AccessoryService;
import com.toolsapp.service.tools.CuttingToolService;
import com.toolsapp.service.tools.MeasuringToolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GeneralProductService {

    private final ProductService productService;
    private final AccessoryService accessoryService;
    private final CuttingToolService cuttingToolService;
    private final MeasuringToolService measuringToolService;

    public GeneralProductService(ProductService productService,
                                 AccessoryService accessoryService,
                                 CuttingToolService cuttingToolService,
                                 MeasuringToolService measuringToolService) {
        this.productService = productService;
        this.accessoryService = accessoryService;
        this.cuttingToolService = cuttingToolService;
        this.measuringToolService = measuringToolService;
    }

    public List<Product> findAllProducts() {
        return productService.findAll();
    }

    public void saveProduct(Product product) {
        productService.save(product);
    }

    public Product findProductById(long id) {
        return productService.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public void deleteProductById(long id) {
        productService.deleteById(id);
    }

    public List<AbstractTool> findAllTools() {
        List<Accessory> accessories = accessoryService.findAll();
        List<CuttingTool> cuttingTools = cuttingToolService.findAll();
        List<MeasuringTool> measuringTools = measuringToolService.findAll();
        return Stream.of(accessories, cuttingTools, measuringTools)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public List<Accessory> findAllAccessories() {
        return accessoryService.findAll();
    }

    public List<CuttingTool> findAllCuttingTools() {
        return cuttingToolService.findAll();
    }

    public List<MeasuringTool> findAllMeasuringTools() {
        return measuringToolService.findAll();
    }

    @Transactional
    public void addAccessory(long id, long toolId) {
        Accessory accessory = accessoryService
                .findById(id).orElseThrow(NoSuchElementException::new);
        Product product = findProductById(id);
        product.getAccessorySet().add(accessory);
        productService.save(product);
    }

    @Transactional
    public void addCuttingTool(long id, long toolId) {
        CuttingTool cuttingTool = cuttingToolService
                .findById(id).orElseThrow(NoSuchElementException::new);
        Product product = findProductById(id);
        product.getCuttingToolsSet().add(cuttingTool);
        productService.save(product);
    }

    @Transactional
    public void addMeasuringTool(long id, long toolId) {
        MeasuringTool measuringTool = measuringToolService
                .findById(id).orElseThrow(NoSuchElementException::new);
        Product product = findProductById(id);
        product.getMeasuringToolsSet().add(measuringTool);
        productService.save(product);
    }
}
