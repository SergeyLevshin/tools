package com.toolsapp.service.extra.extended;

import com.toolsapp.models.extra.Product;
import com.toolsapp.models.tools.AbstractTool;
import com.toolsapp.service.extra.ProductService;
import com.toolsapp.service.tools.ToolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public abstract class AbstractProductAndToolService<E extends AbstractTool> {

    private final ProductService productService;
    private final ToolService<E> toolService;

    public AbstractProductAndToolService(ProductService productService, ToolService<E> toolService) {
        this.productService = productService;
        this.toolService = toolService;
    }

    public List<Product> findAllProducts() {
        return productService.findAll();
    }

    public List<E> findAllTools() {
        return toolService.findAll();
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

    @Transactional
    public void addTool(long id, long toolId) {
        E tool = findTool(toolId);
        Product product = findProductById(id);
        addToolToSet(product, tool);
        saveProduct(product);
    }

    private E findTool(long toolId) {
        return toolService.findById(toolId).orElseThrow(NoSuchElementException::new);
    }

    protected abstract void addToolToSet(Product product, E tool);
}
