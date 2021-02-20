package com.toolsapp.service.extra.product;

import com.toolsapp.models.extra.Product;
import com.toolsapp.models.tools.AbstractTool;
import com.toolsapp.models.tools.CuttingTool;
import com.toolsapp.models.tools.MeasuringTool;
import com.toolsapp.models.tools.SupportTool;
import com.toolsapp.repository.extra.ProductRepository;
import com.toolsapp.service.tools.common.GeneralToolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final GeneralToolService toolService;

    public ProductServiceImpl(ProductRepository repository, GeneralToolService toolService) {
        this.repository = repository;
        this.toolService = toolService;
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    public void save(Product product) {
        repository.save(product);
    }

    @Override
    public Product findById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    //There are only Tool methods below.

    @Override
    public List<AbstractTool> getAllTools() {
        return toolService.findAllTools();
    }

    @Override
    public List<SupportTool> getAllSupportTools() {
        return toolService.findAllSupportTools();
    }

    @Override
    public List<CuttingTool> getAllCuttingTools() {
        return toolService.findAllCuttingTools();
    }

    @Override
    public List<MeasuringTool> getAllMeasuringTools() {
        return toolService.findAllMeasuringTools();
    }

    @Override
    @Transactional
    public void addTool(long productId, long toolId) {
        Optional<AbstractTool> tool = getAllTools()
                .stream()
                .filter(t -> t.getId() == productId)
                .findFirst();
        tool.ifPresent(t -> saveTool(productId, t));
    }

    @Transactional
    private void saveTool(long productId, AbstractTool tool) {
        Product product = findById(productId);
        product.getToolSet().add(tool);
        save(product);
    }
}
