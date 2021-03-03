package com.toolsapp.rest.tool.common;

import com.toolsapp.domain.tools.MeasuringTool;
import com.toolsapp.service.tools.common.MeasuringToolService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/tool/measuring")
public class RestMeasuringToolController
        extends AbstractRestToolController<MeasuringTool, MeasuringToolService> {

    public RestMeasuringToolController(MeasuringToolService service) {
        super(service);
    }

}
