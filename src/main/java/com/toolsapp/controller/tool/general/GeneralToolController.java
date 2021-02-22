package com.toolsapp.controller.tool.general;

import com.toolsapp.service.tools.common.GeneralToolService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/tool")
public class GeneralToolController {

    private final GeneralToolService service;

    public GeneralToolController(GeneralToolService service) {
        this.service = service;
    }

    @GetMapping("/show")
    public String showListOfTools(Model model) {
        model.addAttribute("tools",
                service.findAll());
        return "/tool/show";
    }

    @GetMapping("/delete")
    public String delete(Model model) {
        model.addAttribute("tools",
                service.findAll());
        return "/tool/delete";
    }

    @PostMapping("/delete")
    public String deleteTool(@Valid long id) {
        service.deleteById(id);
        return "redirect:/tool/show";
    }
}
