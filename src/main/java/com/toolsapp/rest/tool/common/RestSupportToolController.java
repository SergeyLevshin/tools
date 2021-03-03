package com.toolsapp.rest.tool.common;

import com.toolsapp.domain.tools.SupportTool;
import com.toolsapp.service.tools.common.SupportToolService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/tool/support")
public class RestSupportToolController
        extends AbstractRestToolController<SupportTool, SupportToolService> {

    public RestSupportToolController(SupportToolService service) {
        super(service);
    }
}
