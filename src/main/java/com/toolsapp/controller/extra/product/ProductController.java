package com.toolsapp.controller.extra.product;

import com.toolsapp.models.extra.Product;
import com.toolsapp.service.extra.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public String showListOfProduct(Model model) {
        model.addAttribute("products", service.findAll());
        return "/product/products";
    }

    @GetMapping("/addProduct")
    public String addProduct(@ModelAttribute("product") Product product,
                             Model model) {
        model.addAttribute("tools", service.getAllTools());
        return "/product/addProduct";
    }

    @PostMapping("/addProduct")
    public String addNewProduct(@ModelAttribute("product") @Valid Product product,
                                BindingResult bindingResult, @Valid long toolId,
                                Model model) {
        model.addAttribute("accessories", service.getAllSupportTools());
        model.addAttribute("cuttingTools", service.getAllCuttingTools());
        model.addAttribute("measuringTools", service.getAllMeasuringTools());
        if (bindingResult.hasErrors())
            return "/product/addProduct";
        service.save(product);
        return "redirect:/product/products";
    }

    @GetMapping("/products/{id}")
    public String singeProductView(@PathVariable("id") long id,
                                   Model model) {
        model.addAttribute("product", service.findById(id));
        model.addAttribute("supportTools", service.getAllSupportTools());
        model.addAttribute("cuttingTools", service.getAllCuttingTools());
        model.addAttribute("measuringTools", service.getAllMeasuringTools());
        return "/product/productInfo";
    }

    @PostMapping("/products/support/{id}")
    public String addSupportTool(@PathVariable("id") long productId,
                                @Valid long toolId) {
        service.addTool(productId,toolId);
        return "/product/products";
    }

    @PostMapping("/products/cutting/{id}")
    public String addCuttingTool(@PathVariable("id") long productId,
                          @Valid long toolId) {
        service.addTool(productId,toolId);
        return "/product/products";
    }

    @PostMapping("/products/measuring/{id}")
    public String addTool(@PathVariable("id") long productId,
                          @Valid long toolId) {
        service.addTool(productId,toolId);
        return "/product/products";
    }

    @PostMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") long productId) {
        service.deleteById(productId);
        return "redirect:/product/products";
    }
}
