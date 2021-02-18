package com.toolsapp.controller.product;

import com.toolsapp.models.extra.Product;
import com.toolsapp.service.extra.product.extended.GeneralProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final GeneralProductService service;

    public ProductController(GeneralProductService service) {
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
        model.addAttribute("accessories", service.findAllAccessories());
        model.addAttribute("cuttingTools", service.findAllCuttingTools());
        model.addAttribute("measuringTools", service.findAllMeasuringTools());
        if (bindingResult.hasErrors())
            return "/product/addProduct";
        service.saveProduct(product);
        return "redirect:/product/products";
    }

    @GetMapping("/products/{id}")
    public String singeProductView(@PathVariable("id") long id,
                                   Model model) {
        model.addAttribute("product", service.findProductById(id));
        model.addAttribute("accessories", service.findAllAccessories());
        model.addAttribute("cuttingTools", service.findAllCuttingTools());
        model.addAttribute("measuringTools", service.findAllMeasuringTools());
        return "/product/productInfo";
    }

    @PostMapping("/products/accessory/{id}")
    public String addAccessory(@PathVariable("id") long id,
                                @Valid long toolId) {
        service.addAccessory(id,toolId);
        return "/product/products";
    }

    @PostMapping("/products/cutting/{id}")
    public String addCuttingTool(@PathVariable("id") long id,
                          @Valid long toolId) {
        service.addCuttingTool(id,toolId);
        return "/product/products";
    }

    @PostMapping("/products/measuring/{id}")
    public String addTool(@PathVariable("id") long id,
                          @Valid long toolId) {
        service.addMeasuringTool(id,toolId);
        return "/product/products";
    }

    @PostMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        service.deleteProductById(id);
        return "redirect:/product/products";
    }
}
