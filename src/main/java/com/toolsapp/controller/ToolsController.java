package com.toolsapp.controller;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.extra.property.Group;
import com.toolsapp.models.extra.property.Producer;
import com.toolsapp.models.tools.CuttingTool;
import com.toolsapp.service.tools.CuttingToolService;
import com.toolsapp.service.extra.WorkerService;
import com.toolsapp.service.extra.property.GroupService;
import com.toolsapp.service.extra.property.ProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class ToolsController {

    private final CuttingToolService cuttingToolService;
    private final WorkerService workerService;
    private final GroupService groupService;
    private final ProducerService producerService;

    public ToolsController(CuttingToolService cuttingToolService, WorkerService workerService, GroupService groupService, ProducerService producerService) {
        this.cuttingToolService = cuttingToolService;
        this.workerService = workerService;
        this.groupService = groupService;
        this.producerService = producerService;
    }

    @GetMapping("/show")
    public String show(Model model) {
        List<CuttingTool> tools = cuttingToolService.findAll();
        model.addAttribute("tools", tools);
        return "/show";
    }

    @GetMapping("addCuttingTool")
    public String add(Model model, @ModelAttribute("tool") CuttingTool tool) {
        List<Producer> producers = producerService.findAll();
        List<Group> groups = groupService.findAll();
        model.addAttribute("producers", producers);
        model.addAttribute("groups", groups);
        return "/addCuttingTool";
    }

    @PostMapping("addCuttingTool")
    public String addCuttingTool(@ModelAttribute("tool") @Valid CuttingTool tool,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Producer> producers = producerService.findAll();
            List<Group> groups = groupService.findAll();
            model.addAttribute("producers", producers);
            model.addAttribute("groups", groups);
            return "/addCuttingTool";
        }
        cuttingToolService.save(tool);
        return "redirect:/show";
    }

    @GetMapping("give")
    public String give(Model model) {
        List<Worker> workers = workerService.findAll();
        List<CuttingTool> tools = cuttingToolService.findAll();
        model.addAttribute("workers", workers);
        model.addAttribute("tools", tools);
        return "/give";
    }

    @PostMapping("/give")
    public String giveTool(@Valid long toolId,
                           int quantity, @Valid long workerId) {
        cuttingToolService.giveToolToWorker(toolId, quantity, workerId);
        return "redirect:/show";
    }

    @GetMapping("delete")
    public String delete(Model model) {
        List<CuttingTool> tools = cuttingToolService.findAll();
        model.addAttribute("tools", tools);
        return "/delete";
    }

    @PostMapping("delete")
    public String deleteItem(@Valid long id) {
        cuttingToolService.delete(id);
        return "redirect:/show";
    }

}
