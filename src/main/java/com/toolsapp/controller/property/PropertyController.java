package com.toolsapp.controller.property;

import com.toolsapp.domain.property.Producer;
import com.toolsapp.domain.property.ToolFunction;
import com.toolsapp.service.property.extended.ExtendedPropertyService;
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

    private final ExtendedPropertyService service;

    public PropertyController(ExtendedPropertyService service) {
        this.service = service;
    }

    @GetMapping("/showToolProperties")
    public String allProperties(Model model) {
        model.addAttribute("toolFunctions", service.findAllToolFunctions());
        model.addAttribute("producers", service.findAllProducers());
        return "/property/showToolProperties";
    }

    @GetMapping("/addToolProperties")
    public String addToolProperties(@ModelAttribute("toolFunction") ToolFunction toolFunction,
                                    @ModelAttribute("producer") Producer producer) {
        return "/property/addToolProperties";
    }
    @PostMapping("/addToolFunction")
    public String addGroup(@ModelAttribute("toolFunction") @Valid ToolFunction toolFunction,
                           BindingResult bindingResult,
                           Producer producer) {
        if (bindingResult.hasErrors()) {
            return "/property/addToolProperties";
        }
        service.saveToolFunction(toolFunction);
        return "redirect:/property/showToolProperties";
    }

    @PostMapping("/addProducer")
    public String addGroup(@ModelAttribute("producer") @Valid Producer producer,
                           BindingResult bindingResult,
                           ToolFunction toolFunction) {
        if (bindingResult.hasErrors())
            return "/property/addToolProperties";
        service.saveProducer(producer);
        return "redirect:/property/showToolProperties";
    }

    @GetMapping("/deleteToolProperty")
    public String deleteProperty(Model model) {
        model.addAttribute("toolFunctions", service.findAllToolFunctions());
        model.addAttribute("producers", service.findAllProducers());
        return "/property/deleteToolProperty";
    }

    @PostMapping("/deleteToolFunction")
    public String deleteToolFunction(@Valid long id) {
        service.deleteToolFunctionById(id);
        return "redirect:/property/showToolProperties";
    }

    @PostMapping("/deleteProducer")
    public String deleteProducer(@Valid long id) {
        service.deleteProducerById(id);
        return "redirect:/property/showToolProperties";
    }
}
