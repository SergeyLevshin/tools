package com.toolsapp.controller;

import com.toolsapp.models.extra.Product;
import com.toolsapp.service.extra.ProductService;
import com.toolsapp.service.tools.CuttingToolService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CuttingToolService cuttingToolService;

    public ProductController(ProductService productService, CuttingToolService instrumentService) {
        this.productService = productService;
        this.cuttingToolService = instrumentService;
    }

    @GetMapping("/products")
    public String showListOfProduct(Model model) {
        model.addAttribute("products", productService.findAll());
        return "/product/products";
    }

    @GetMapping("/addProduct")
    public String addProduct(@ModelAttribute("product") Product product,
                             Model model) {
        model.addAttribute("tools", cuttingToolService.findAll());
        return "/product/addProduct";
    }

    @PostMapping("/addProduct")
    public String addNewProduct(@ModelAttribute("product") @Valid Product product,
                                BindingResult bindingResult, @Valid long toolId,
                                Model model) {
        model.addAttribute("tools", cuttingToolService.findAll());
        if (bindingResult.hasErrors())
            return "/product/addProduct";
        //TODO something wrong, idk what
//        productService.updateInstrumentList(product.getId(), toolId);
        productService.save(product);

        return "redirect:/product/products";
    }

    @GetMapping("/products/{id}")
    public String singeProductView(@PathVariable("id") long id,
                                   Model model) {
        model.addAttribute("product", productService.findById(id).orElseThrow());
        model.addAttribute("tools", cuttingToolService.findAll());
        return "/product/productInfo";
    }

    @PostMapping("/products/{id}")
    public String addInstrument(@PathVariable("id") long id,
                                @Valid long toolId) {
        productService.updateInstrumentList(id,toolId);
        return "/product/products";
    }

    @PostMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        productService.deleteById(id);
        return "redirect:/product/products";
    }
}
