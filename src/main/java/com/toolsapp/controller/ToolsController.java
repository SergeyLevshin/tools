package com.toolsapp.controller;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.instrument.CuttingTool;
import com.toolsapp.service.InstrumentService;
import com.toolsapp.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class ToolsController {

    private final InstrumentService instrumentService;
    private final WorkerService workerService;

    @Autowired
    public ToolsController(InstrumentService instrumentService, WorkerService workerService) {
        this.instrumentService = instrumentService;
        this.workerService = workerService;
    }

    @GetMapping("/show")
    public String show(Model model) {
        List<CuttingTool> tools = new ArrayList<>(instrumentService.findAll());
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
        instrumentService.save(tool);
        return "redirect:/show";
    }

    @GetMapping("give")
    public String give(Model model) {
        List<Worker> workers = workerService.findAll();
        List<CuttingTool> tools = instrumentService.findAll();
        model.addAttribute("workers", workers);
        model.addAttribute("tools", tools);
        return "/give";
    }

    @PostMapping("/give")
    public String giveTool(CuttingTool tool,
                           int quantity, Worker worker) {
//        instrumentService.giveToolToWorker(tool, quantity, worker);
        return "redirect:/show";
    }

    @GetMapping("delete")
    public String delete(Model model) {
        List<CuttingTool> tools = instrumentService.findAll();
        model.addAttribute("tools", tools);
        return "/delete";
    }

    @PostMapping("delete")
    public String deleteItem(CuttingTool tool) {
        instrumentService.delete(tool);
        return "redirect:/show";
    }

}
