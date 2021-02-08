package com.toolsapp.controller;

import com.toolsapp.models.extra.property.Group;
import com.toolsapp.models.extra.property.Producer;
import com.toolsapp.service.extra.property.GroupService;
import com.toolsapp.service.extra.property.ProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/property")
public class PropertyController {

    private final GroupService groupService;
    private final ProducerService producerService;

    public PropertyController(GroupService groupService, ProducerService producerService) {
        this.groupService = groupService;
        this.producerService = producerService;
    }

    @GetMapping("/showToolProperties")
    public String allProperties(Model model) {
        List<Group> groups = groupService.findAll();
        List<Producer> producers = producerService.findAll();
        model.addAttribute("groups", groups);
        model.addAttribute("producers", producers);
        return "/property/showToolProperties";
    }

    @GetMapping("/addToolProperties")
    public String addToolProperties(){
        return "/property/addToolProperties";
    }
    @PostMapping("/addGroup")
    public String addGroup(Group group) {
        groupService.save(group);
        return "redirect:/property/showToolProperties";
    }

    @PostMapping("/addProducer")
    public String addGroup(Producer producer) {
        producerService.save(producer);
        return "redirect:/property/showToolProperties";
    }

    @GetMapping("/deleteToolProperty")
    public String deleteProperty(Model model) {
        List<Group> groups = groupService.findAll();
        List<Producer> producers = producerService.findAll();
        model.addAttribute("groups", groups);
        model.addAttribute("producers", producers);
        return "/property/deleteToolProperty";
    }

    @PostMapping("/deleteGroup")
    public String deleteGroup(@Valid long id) {
        groupService.deleteById(id);
        return "redirect:/property/showToolProperties";
    }

    @PostMapping("/deleteProducer")
    public String deleteProducer(@Valid long id) {
        producerService.deleteById(id);
        return "redirect:/property/showToolProperties";
    }
}
