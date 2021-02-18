//package com.toolsapp.controller.extra;
//
//import com.toolsapp.models.tools.AbstractTool;
//import com.toolsapp.service.extra.worker.extended.AbstractWorkerAndToolService;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.validation.Valid;
//
//@Controller
//@RequestMapping("/worker")
//public class WorkerController<E extends AbstractTool> {
//
//    private final AbstractWorkerAndToolService<E> service;
//
//    public WorkerController(AbstractWorkerAndToolService<E> service) {
//        this.service = service;
//    }
//
//    @GetMapping("/workerList")
//    public String showWorkers(Model model){
//        model.addAttribute("workers",
//                service.findAllWorkers());
//        return ("/worker/workerList");
//    }
//
//    @GetMapping("/workers/{id}")
//    public String singleWorkerToolsList(@PathVariable("id") long id, Model model) {
//        model.addAttribute("worker",
//                service.findWorkerById(id));
//        model.addAttribute("tools",
//                service.getWorkerTools(id));
//        return "worker/workerInfo";
//    }
//
//    @PostMapping("/remove/{id}")
//    public String removeToolFromWorker(@PathVariable("id") long workerId,
//                             @Valid long toolId){
//        service.removeToolFromWorker(workerId, toolId);
//        return "redirect:/worker/workerList";
//    }
//}
