package com.toolsapp.controller.tool;

import com.toolsapp.models.tools.AbstractTool;
import com.toolsapp.service.tools.extended.ExtendedToolService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

public abstract class AbstractToolController<E extends AbstractTool,
        S extends ExtendedToolService<E>> implements CommonToolController<E>{

    private final S service;

    protected AbstractToolController(S service) {
        this.service = service;
    }


    @GetMapping("/show")
    public String showListOfTools(Model model) {
        model.addAttribute("tools",
                service.findAllTools());
        return "/show";
    }

    @GetMapping("addAccessory")
    public String addTool(Model model, @ModelAttribute("tool") E tool) {
        model.addAttribute("producers",
               service.findAllProducers());
        model.addAttribute("types",
                service.findAllToolFunctions());
        return "/addAccessory";
    }

    @PostMapping("addAccessory")
    public String addingTool(@ModelAttribute("tool") @Valid E tool,
                                  BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("producers",
                    service.findAllProducers());
            model.addAttribute("types",
                    service.findAllToolFunctions());
            return "/addAccessory";
        }
        service.saveTool(tool);
        return "redirect:/show";
    }

    @GetMapping("give")
    public String give(Model model) {
        model.addAttribute("workers",
                service.findAllWorkers());
        model.addAttribute("tools",
                service.findAllTools());
        return "/give";
    }

    @PostMapping("/give")
    public String giveTool(@Valid long toolId,
                           int quantity, @Valid long workerId) {
        service.giveToolToWorker(toolId, quantity, workerId);
        return "redirect:/show";
    }

    @GetMapping("delete")
    public String delete(Model model) {
        model.addAttribute("tools",
                service.findAllTools());
        return "/delete";
    }

    @PostMapping("delete")
    public String deleteTool(@Valid long id) {
        service.deleteToolById(id);
        return "redirect:/show";
    }
}

