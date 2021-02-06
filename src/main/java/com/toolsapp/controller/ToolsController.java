package com.toolsapp.controller;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.tools.CuttingTool;
import com.toolsapp.service.CuttingToolService;
import com.toolsapp.service.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class ToolsController {

    private final CuttingToolService cuttingToolService;
    private final WorkerService workerService;

    public ToolsController(CuttingToolService cuttingToolService, WorkerService workerService) {
        this.cuttingToolService = cuttingToolService;
        this.workerService = workerService;
    }

    @GetMapping("/show")
    public String show(Model model) {
        List<CuttingTool> tools = cuttingToolService.findAll();
        model.addAttribute("tools",
                tools);
        return "/show";
    }

    @GetMapping("addCuttingTool")
    public String add() {
        return "/addCuttingTool";
    }

    @PostMapping("addCuttingTool")
    public String addCuttingTool(CuttingTool tool) {
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
