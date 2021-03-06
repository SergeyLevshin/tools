package com.toolsapp.rest.tool.common;

import com.toolsapp.domain.tools.MeasuringTool;
import com.toolsapp.service.tools.common.MeasuringToolService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(RestMeasuringToolController.class)
public class RestMeasuringToolControllerTest
        extends AbstractRestToolControllerTest<MeasuringTool, MeasuringToolService> {

    public RestMeasuringToolControllerTest() {
        super("/rest/tool/measuring/");
    }

    @Override
    protected MeasuringTool createTool() {
        return new MeasuringTool();
    }
}
