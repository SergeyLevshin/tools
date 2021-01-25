package com.toolsapp.controller;

import com.toolsapp.models.instrument.Accessory;
import com.toolsapp.models.instrument.CuttingTool;
import com.toolsapp.models.instrument.Instrument;
import com.toolsapp.repository.InstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class ToolsController {

    private InstrumentRepository repository;

    @Autowired
    public ToolsController(InstrumentRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public String show(Model model) {
        List<Instrument> instruments = new ArrayList<>();
        repository.findAll()
                .forEach(i -> instruments.add(i));
        model.addAttribute("instruments",
                instruments);
        for (Instrument instrument : instruments)
            System.out.println(instrument);
        return "/show";
    }

    @GetMapping("/addCuttingTool")
    public String add() {
        return "/addCuttingTool";
    }

    @PostMapping("/addCuttingTool")
    public String addCuttingTool(@ModelAttribute("tool") CuttingTool tool) {
        repository.save(tool);
        return "redirect:/show";
    }
}
