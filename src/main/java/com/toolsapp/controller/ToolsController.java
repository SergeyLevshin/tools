package com.toolsapp.controller;

import com.toolsapp.models.tools.CuttingTool;
import com.toolsapp.service.combined.CuttingToolAndWorkerService;
import com.toolsapp.service.extra.property.PropertiesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class ToolsController {

    private final CuttingToolAndWorkerService cuttingToolAndWorkerService;
    private final PropertiesService propertiesService;

    public ToolsController(CuttingToolAndWorkerService cuttingToolAndWorkerService, PropertiesService propertiesService) {
        this.cuttingToolAndWorkerService = cuttingToolAndWorkerService;
        this.propertiesService = propertiesService;
    }

    @GetMapping("/show")
    public String show(Model model) {
        model.addAttribute("tools",
                cuttingToolAndWorkerService.getCuttingToolService().findAll());
        return "/show";
    }

    @GetMapping("addCuttingTool")
    public String add(Model model, @ModelAttribute("tool") CuttingTool tool) {
        model.addAttribute("producers",
                propertiesService.getProducerService().findAll());
        model.addAttribute("groups",
                propertiesService.getGroupService().findAll());
        return "/addCuttingTool";
    }

    @PostMapping("addCuttingTool")
    public String addCuttingTool(@ModelAttribute("tool") @Valid CuttingTool tool,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("producers",
                    propertiesService.getProducerService().findAll());
            model.addAttribute("groups",
                    propertiesService.getGroupService().findAll());
            return "/addCuttingTool";
        }
        cuttingToolAndWorkerService.getCuttingToolService().save(tool);
        return "redirect:/show";
    }

    @GetMapping("give")
    public String give(Model model) {
        model.addAttribute("workers",
                cuttingToolAndWorkerService.getWorkerService().findAll());
        model.addAttribute("tools",
                cuttingToolAndWorkerService.getCuttingToolService().findAll());
        return "/give";
    }

    @PostMapping("/give")
    public String giveTool(@Valid long toolId,
                           int quantity, @Valid long workerId) {
        cuttingToolAndWorkerService.giveToolToWorker(toolId, quantity, workerId);
        return "redirect:/show";
    }

    @GetMapping("delete")
    public String delete(Model model) {
        model.addAttribute("tools",
                cuttingToolAndWorkerService.getCuttingToolService().findAll());
        return "/delete";
    }

    @PostMapping("delete")
    public String deleteItem(@Valid long id) {
        cuttingToolAndWorkerService.getCuttingToolService().deleteById(id);
        return "redirect:/show";
    }

}
