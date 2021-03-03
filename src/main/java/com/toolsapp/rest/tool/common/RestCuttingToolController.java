package com.toolsapp.rest.tool.common;

import com.toolsapp.domain.tools.CuttingTool;
import com.toolsapp.service.tools.common.CuttingToolService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/tool/cutting")
public class RestCuttingToolController
        extends AbstractRestToolController<CuttingTool, CuttingToolService> {

    public RestCuttingToolController(CuttingToolService service) {
        super(service);
    }
}
