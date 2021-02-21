package com.toolsapp.controller.tool.general;

import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.service.tools.common.GeneralToolService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
                service.findAllTools());
        return "/tool/show";
    }
}
