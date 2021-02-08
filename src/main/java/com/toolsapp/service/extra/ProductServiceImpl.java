package com.toolsapp.service.extra;

import com.toolsapp.models.extra.Product;
import com.toolsapp.models.tools.CuttingTool;
import com.toolsapp.repository.extra.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
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
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void updateInstrumentList(Product product, CuttingTool tool) {
        product.getCuttingTools().add(tool);
        repository.save(product);
    }

    public Map<CuttingTool, Integer> workersTools(long id) {

        return null;
    }
}
