package com.toolsapp.service.extra;

import com.toolsapp.models.extra.Product;
import com.toolsapp.models.tools.CuttingTool;
import com.toolsapp.repository.extra.ProductRepository;
import com.toolsapp.repository.tools.CuttingToolsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CuttingToolsRepository cuttingToolsRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              CuttingToolsRepository cuttingToolsRepository) {
        this.productRepository = productRepository;
        this.cuttingToolsRepository = cuttingToolsRepository;
    }


    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(long id) {
        return Optional
                .ofNullable(productRepository
                        .findById(id).orElseThrow(NoSuchElementException::new));
    }

    @Override
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateInstrumentList(long id, long toolId) {
        Product product = productRepository.findById(id).orElseThrow();
        CuttingTool tool = cuttingToolsRepository.findById(toolId)
                .orElseThrow(NoSuchElementException::new);
        product.getCuttingTools().add(tool);
        productRepository.save(product);
    }
}
