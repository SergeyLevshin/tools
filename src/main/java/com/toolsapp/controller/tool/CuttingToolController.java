package com.toolsapp.controller.tool;

import com.toolsapp.controller.tool.AbstractToolController;
import com.toolsapp.domain.tools.CuttingTool;
import com.toolsapp.service.tools.extended.ExtendedCuttingToolService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tool/cuttingTool")
public class CuttingToolController
        extends AbstractToolController<CuttingTool, ExtendedCuttingToolService> {
    protected CuttingToolController(ExtendedCuttingToolService service) {
        super(service, "cuttingTool");
    }
}
