package com.toolsapp.controller;

import com.toolsapp.models.extra.Product;
import com.toolsapp.service.CuttingToolServiceImpl;
import com.toolsapp.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CuttingToolServiceImpl instrumentService;

    public ProductController(ProductService productService, CuttingToolServiceImpl instrumentService) {
        this.productService = productService;
        this.instrumentService = instrumentService;
    }

    @GetMapping("/products")
    public String showListOfProduct(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "/product/products";
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model) {
        model.addAttribute("tools", instrumentService.findAll());
        return "/product/addProduct";
    }

    @PostMapping("/addProduct")
    public String addNewProduct(@Valid long toolId, Product product) {
        product.getCuttingTools().add(instrumentService.findById(toolId));
        productService.save(product);
        return "redirect:/product/products";
    }

    @GetMapping("/products/{id}")
    public String singeProductView(@PathVariable("id") long id,
                                   Model model) {
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("tools", instrumentService.findAll());
        return "/product/productInfo";
    }

    @PostMapping("/products/{id}")
    public String addInstrument(@PathVariable("id") long id,
                                @Valid long toolId) {
        productService.updateInstrumentList(productService.findById(id),
                instrumentService.findById(toolId));
        return "/product/products";
    }

    @PostMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        productService.deleteById(id);
        return "redirect:/product/products";
    }
}
