package com.toolsapp.controller.tool;

import com.toolsapp.domain.tools.MeasuringTool;
import com.toolsapp.service.tools.extended.ExtendedMeasuringToolService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tool/measuringTool")
public class MeasuringToolController
        extends AbstractToolController<MeasuringTool, ExtendedMeasuringToolService> {
    protected MeasuringToolController(ExtendedMeasuringToolService service) {
        super(service, "measuringTool");
    }
}
