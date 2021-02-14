package com.toolsapp.controller;

import com.toolsapp.models.extra.property.ToolType;
import com.toolsapp.models.extra.property.Producer;
import com.toolsapp.service.extra.property.ToolTypeService;
import com.toolsapp.service.extra.property.ProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/property")
public class PropertyController {

    private final ToolTypeService toolTypeService;
    private final ProducerService producerService;

    public PropertyController(ToolTypeService toolTypeService, ProducerService producerService) {
        this.toolTypeService = toolTypeService;
        this.producerService = producerService;
    }

    @GetMapping("/showToolProperties")
    public String allProperties(Model model) {
        model.addAttribute("types", toolTypeService.findAll());
        model.addAttribute("producers", producerService.findAll());
        return "/property/showToolProperties";
    }

    @GetMapping("/addToolProperties")
    public String addToolProperties(@ModelAttribute("toolType") ToolType toolType,
                                    @ModelAttribute("producer") Producer producer) {
        return "/property/addToolProperties";
    }
    @PostMapping("/addToolType")
    public String addGroup(@ModelAttribute("toolType") @Valid ToolType toolType,
                           BindingResult bindingResult,
                           Producer producer) {
        if (bindingResult.hasErrors())
            return "/property/addToolProperties";
        toolTypeService.save(toolType);
        return "redirect:/property/showToolProperties";
    }

    @PostMapping("/addProducer")
    public String addGroup(@ModelAttribute("producer") @Valid Producer producer,
                           BindingResult bindingResult,
                           ToolType toolType) {
        if (bindingResult.hasErrors())
            return "/property/addToolProperties";
        producerService.save(producer);
        return "redirect:/property/showToolProperties";
    }

    @GetMapping("/deleteToolProperty")
    public String deleteProperty(Model model) {
        model.addAttribute("types", toolTypeService.findAll());
        model.addAttribute("producers", producerService.findAll());
        return "/property/deleteToolProperty";
    }

    @PostMapping("/deleteToolType")
    public String deleteGroup(@Valid long id) {
        toolTypeService.deleteById(id);
        return "redirect:/property/showToolProperties";
    }

    @PostMapping("/deleteProducer")
    public String deleteProducer(@Valid long id) {
        producerService.deleteById(id);
        return "redirect:/property/showToolProperties";
    }
}
