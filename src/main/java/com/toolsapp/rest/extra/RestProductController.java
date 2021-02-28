package com.toolsapp.rest.extra;

import com.toolsapp.domain.extra.Product;
import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.service.extra.product.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/rest/product")
public class RestProductController {

    private final ProductService service;

    public RestProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        final List<Product> products = service.findAllSortByName();
        return products != null && !products.isEmpty()
                ? new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") long id) {
        Optional<Product> product = service.findById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> addTool(@PathVariable("id") long productId,
                                           @Valid @RequestBody Map<String, Long> data) {
        service.addTool(productId, data.get("toolId"));
        Optional<Product> product = service.findById(productId);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_MODIFIED));
    }

    @GetMapping("/addProduct")
    public ResponseEntity<List<? extends AbstractTool>> addProduct() {
        return new ResponseEntity<>(service.getAllTools(), HttpStatus.OK);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product) {
        service.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") long id) {
        final Optional<Product> product = service.findById(id);
        service.deleteById(id);
        return product.isPresent()
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
