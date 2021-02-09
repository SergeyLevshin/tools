package com.toolsapp.controller;

import com.toolsapp.models.extra.Product;
import com.toolsapp.models.tools.CuttingTool;
import com.toolsapp.service.tools.CuttingToolServiceImpl;
import com.toolsapp.service.extra.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String addProduct(@ModelAttribute("product") Product product,
                             Model model) {
        model.addAttribute("tools", instrumentService.findAll());
        return "/product/addProduct";
    }

    @PostMapping("/addProduct")
    public String addNewProduct(@ModelAttribute("product") @Valid Product product,
                                BindingResult bindingResult, @Valid long toolId,
                                Model model) {
        if (bindingResult.hasErrors()) {
            List<CuttingTool> tools = instrumentService.findAll();
            model.addAttribute("tools", tools);
            return "/product/addProduct";
        }
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
