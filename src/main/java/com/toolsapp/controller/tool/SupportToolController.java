package com.toolsapp.controller.tool;

import com.toolsapp.domain.tools.SupportTool;
import com.toolsapp.service.tools.extended.ExtendedSupportToolService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tool/supportTool")
public class SupportToolController
        extends AbstractToolController<SupportTool, ExtendedSupportToolService> {

    protected SupportToolController(ExtendedSupportToolService service) {
        super(service, "supportTool");
    }
}
