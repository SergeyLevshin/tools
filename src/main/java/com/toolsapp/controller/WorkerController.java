package com.toolsapp.controller;

import com.toolsapp.service.combined.CuttingToolAndWorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/worker")
public class WorkerController {

    private final CuttingToolAndWorkerService cuttingToolAndWorkerService;

    public WorkerController(CuttingToolAndWorkerService cuttingToolAndWorkerService) {
        this.cuttingToolAndWorkerService = cuttingToolAndWorkerService;
    }

    @GetMapping("/workerList")
    public String showWorkers(Model model){
        model.addAttribute("workers",
                cuttingToolAndWorkerService.getWorkerService().findAll());
        return ("/worker/workerList");
    }

    @GetMapping("/workers/{id}")
    public String singleWorkerToolsList(@PathVariable("id") long id, Model model) {
        model.addAttribute("worker",
                cuttingToolAndWorkerService.getWorkerService().findById(id).orElseThrow());
        model.addAttribute("tools",
                cuttingToolAndWorkerService.workerTools(id));
        return "worker/workerInfo";
    }

    @PostMapping("/remove/{id}")
    public String removeTool(@PathVariable("id") long workerId,
                             @Valid long toolId,
                             @Valid int quantity){
        cuttingToolAndWorkerService.removeTool(workerId, toolId, quantity);
        return "redirect:/worker/workerList";
    }
}
