package com.toolsapp.controller;

import com.toolsapp.models.instrument.CuttingTool;
import com.toolsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class ToolsController {

    private final UserService service;

    @Autowired
    public ToolsController(UserService service) {
        this.service = service;
    }

    @GetMapping("/show")
    public String show(Model model) {
        List<CuttingTool> tools = new ArrayList<>(service.findAll());
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
        service.save(tool);
        return "redirect:/show";
    }

    @GetMapping("give")
    public String give() {
        return "/give";
    }

    @PatchMapping("/give")
    public String giveTool() {
        return "redirect:/";
    }
}
