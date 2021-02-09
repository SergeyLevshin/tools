package com.toolsapp.controller;

import com.toolsapp.models.extra.property.Group;
import com.toolsapp.models.extra.property.Producer;
import com.toolsapp.service.extra.property.GroupService;
import com.toolsapp.service.extra.property.ProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String addToolProperties(@ModelAttribute("group")Group group,
                                    @ModelAttribute("producer") Producer producer) {
        return "/property/addToolProperties";
    }
    @PostMapping("/addGroup")
    public String addGroup(@ModelAttribute("group") @Valid Group group,
                           BindingResult bindingResult,
                           Producer producer) {
        if (bindingResult.hasErrors())
            return "/property/addToolProperties";
        groupService.save(group);
        return "redirect:/property/showToolProperties";
    }

    @PostMapping("/addProducer")
    public String addGroup(@ModelAttribute("producer") @Valid Producer producer,
                           BindingResult bindingResult,
                           Group group) {
        if (bindingResult.hasErrors())
            return "/property/addToolProperties";
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
