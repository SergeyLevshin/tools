package com.toolsapp.controller;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.service.extra.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/worker")
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("/workerList")
    public String showWorkers(Model model){
        List<Worker> workers = workerService.findAll();
        model.addAttribute("workers", workers);
        return ("/worker/workerList");
    }

    @GetMapping("/workers/{id}")
    public String singleWorkerToolsList(@PathVariable("id") long id, Model model) {
        model.addAttribute("worker", workerService.findById(id));
        model.addAttribute("tools", workerService.workerTools(id));
        return "worker/workerInfo";
    }

    @PostMapping("/remove/{id}")
    public String removeTool(@PathVariable("id") long workerId,
                             @Valid long toolId,
                             @Valid int quantity){
        workerService.removeTool(workerId, toolId, quantity);
        return "redirect:/worker/workerList";
    }
}
