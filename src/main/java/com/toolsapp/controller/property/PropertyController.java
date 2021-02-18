package com.toolsapp.controller.property;

import com.toolsapp.models.property.Producer;
import com.toolsapp.models.property.ToolType;
import com.toolsapp.service.property.PropertiesService;
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

    private final PropertiesService service;

    public PropertyController(PropertiesService service) {
        this.service = service;
    }

    @GetMapping("/showToolProperties")
    public String allProperties(Model model) {
        model.addAttribute("types", service.findAllToolTypes());
        model.addAttribute("producers", service.findAllProducers());
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
        service.saveToolType(toolType);
        return "redirect:/property/showToolProperties";
    }

    @PostMapping("/addProducer")
    public String addGroup(@ModelAttribute("producer") @Valid Producer producer,
                           BindingResult bindingResult,
                           ToolType toolType) {
        if (bindingResult.hasErrors())
            return "/property/addToolProperties";
        service.saveProducer(producer);
        return "redirect:/property/showToolProperties";
    }

    @GetMapping("/deleteToolProperty")
    public String deleteProperty(Model model) {
        model.addAttribute("types", service.findAllToolTypes());
        model.addAttribute("producers", service.findAllProducers());
        return "/property/deleteToolProperty";
    }

    @PostMapping("/deleteToolType")
    public String deleteToolType(@Valid long id) {
        service.deleteToolTypeById(id);
        return "redirect:/property/showToolProperties";
    }

    @PostMapping("/deleteProducer")
    public String deleteProducer(@Valid long id) {
        service.deleteProducerById(id);
        return "redirect:/property/showToolProperties";
    }
}
