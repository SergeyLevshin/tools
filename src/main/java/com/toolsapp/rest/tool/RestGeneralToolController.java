package com.toolsapp.rest.tool;

import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.service.tools.common.GeneralToolService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/tool/general")
public class RestGeneralToolController
        extends AbstractRestToolController<AbstractTool, GeneralToolService> {

    public RestGeneralToolController(GeneralToolService service) {
        super(service);
    }
}
