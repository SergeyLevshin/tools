package com.toolsapp.controller.tool;

import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.service.tools.extended.ExtendedToolService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

public abstract class AbstractToolController<E extends AbstractTool,
        S extends ExtendedToolService<E>>
        implements CommonToolController<E> {

    private final S service;
    private final String toolType;

    protected AbstractToolController(S service, String toolType) {
        this.service = service;
        this.toolType = toolType;
    }

    @Override
    @RequestMapping("/give")
    public String give(Model model) {
        model.addAttribute("workers",
                service.findAllWorkers());
        model.addAttribute("tools",
                service.findAllTools());
        return "/tool/" + toolType + "/give";
    }

    @Override
    @PostMapping("/give")
    public String giveTool(@Valid long toolId,
                           int quantity, @Valid long workerId) {
        service.giveToolToWorker(toolId, quantity, workerId);
        return "redirect:/tool/show";
    }

    @GetMapping("/add")
    public String addTool(@ModelAttribute("tool") E tool,
                          Model model) {
        model.addAttribute("producers",
                service.findAllProducers());
        model.addAttribute("toolFunctions",
                service.findAllToolFunctions());
        return "/tool/" + toolType + "/add";
    }

    @PostMapping("/add")
    public String addingTool(@ModelAttribute("tool") @Valid E tool,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("producers",
                    service.findAllProducers());
            model.addAttribute("toolFunctions",
                    service.findAllToolFunctions());
            return "/tool/" + toolType + "/add";
        }
        service.saveTool(tool);
        System.out.println(tool.getId() + " " + tool.getName());
        return "redirect:/tool/show";
    }
}

