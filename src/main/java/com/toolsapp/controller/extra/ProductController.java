package com.toolsapp.controller.extra;

import com.toolsapp.models.extra.Product;
import com.toolsapp.models.tools.AbstractTool;
import com.toolsapp.service.extra.product.extended.AbstractProductAndToolService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping("/product")
public class ProductController<E extends AbstractTool> {

    private final AbstractProductAndToolService<E> service;

    public ProductController(AbstractProductAndToolService<E> service) {
        this.service = service;
    }

    @GetMapping("/products")
    public String showListOfProduct(Model model) {
        model.addAttribute("products", service.findAllProducts());
        return "/product/products";
    }

    @GetMapping("/addProduct")
    public String addProduct(@ModelAttribute("product") Product product,
                             Model model) {
        model.addAttribute("tools", service.findAllTools());
        return "/product/addProduct";
    }

    @PostMapping("/addProduct")
    public String addNewProduct(@ModelAttribute("product") @Valid Product product,
                                BindingResult bindingResult, @Valid long toolId,
                                Model model) {
        model.addAttribute("tools", service.findAllTools());
        if (bindingResult.hasErrors())
            return "/product/addProduct";
        service.saveProduct(product);

        return "redirect:/product/products";
    }

    @GetMapping("/products/{id}")
    public String singeProductView(@PathVariable("id") long id,
                                   Model model) {
        model.addAttribute("product", service.findProductById(id));
        model.addAttribute("tools", service.findAllTools());
        return "/product/productInfo";
    }

    @PostMapping("/products/{id}")
    public String addInstrument(@PathVariable("id") long id,
                                @Valid long toolId) {
        service.addTool(id,toolId);
        return "/product/products";
    }

    @PostMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        service.deleteProductById(id);
        return "redirect:/product/products";
    }
}
