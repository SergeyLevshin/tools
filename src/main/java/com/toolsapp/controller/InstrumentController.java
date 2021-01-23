package com.toolsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class InstrumentController {

    private InstrumentRepo repo;

    @Autowired
    public InstrumentController(InstrumentRepo repo) {
        this.repo = repo;
    }

    @GetMapping()
    public String show(Model model) {
        model.addAttribute("toolList", InstrumentRepo.getToolList());
        return "/show";
    }
}
