package com.toolsapp.controller;

import com.toolsapp.service.extra.WorkerService;
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

    private final WorkerService service;

    public WorkerController(WorkerService service) {
        this.service = service;
    }

    @GetMapping("/workerList")
    public String showWorkers(Model model){
        model.addAttribute("workers",
                service.findAll());
        return ("/worker/workerList");
    }

    @GetMapping("/workers/{id}")
    public String singleWorkerToolsList(@PathVariable("id") long id, Model model) {
        model.addAttribute("worker",
                service.findById(id));
        model.addAttribute("tools",
                service.workerTools(id));
        return "worker/workerInfo";
    }

    //TODO move to ToolService
    @PostMapping("/remove/{id}")
    public String removeToolFromWorker(@PathVariable("id") long workerId,
                             @Valid long toolId,
                             @Valid int quantity){
        service.removeToolFromWorker(workerId, toolId, quantity);
        return "redirect:/worker/workerList";
    }
}
