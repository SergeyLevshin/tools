package com.toolsapp.service.extra;

import com.toolsapp.models.extra.Product;
import com.toolsapp.repository.extra.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
}
