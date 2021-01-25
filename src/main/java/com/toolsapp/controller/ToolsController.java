package com.toolsapp.controller;

import com.toolsapp.models.instrument.CuttingTool;
import com.toolsapp.repository.CuttingToolsRepository;
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

    private CuttingToolsRepository repository;

    @Autowired
    public ToolsController(CuttingToolsRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public String show(Model model) {
        List<CuttingTool> tools = new ArrayList<>();
        repository.findAll()
                .forEach(tools::add);
        model.addAttribute("tools",
                tools);
        return "/show";
    }

    @GetMapping("addCuttingTool")
    public String add() {
        return "/addCuttingTool";
    }

    @PostMapping("addCuttingTool")
    public String addCuttingTool(CuttingTool tool, Model model) {
        repository.save(tool);
        return "redirect:/";
    }
}
