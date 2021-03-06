package com.toolsapp.rest.tool.common;

import com.toolsapp.domain.tools.CuttingTool;
import com.toolsapp.service.tools.common.CuttingToolService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(RestCuttingToolController.class)
public class RestCuttingToolControllerTest
        extends AbstractRestToolControllerTest<CuttingTool, CuttingToolService> {

    public RestCuttingToolControllerTest() {
        super("/rest/tool/cutting/");
    }

    @Override
    protected CuttingTool createTool() {
        return new CuttingTool();
    }
}
