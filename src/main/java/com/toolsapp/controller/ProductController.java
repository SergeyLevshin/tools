package com.toolsapp.controller;

import com.toolsapp.models.extra.Product;
import com.toolsapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String showListOfProduct(Model model) {
        List<Product> products = new ArrayList<>(productService.findAll());
        model.addAttribute("products", products);
        return "/product/products";
    }

    @GetMapping("/addProduct")
    public String addProduct() {
        return "/product/addProduct";
    }

    @PostMapping("/addProduct")
    public String addNewProduct(Product product) {
        productService.save(product);
        return "redirect:/product/products";
    }
}
