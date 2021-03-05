package com.toolsapp.rest.tool.common;

import com.toolsapp.domain.tools.SupportTool;
import com.toolsapp.service.tools.common.SupportToolService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(RestSupportToolController.class)
public class RestSupportToolControllerTest
        extends AbstractRestToolControllerTest<SupportTool, SupportToolService> {
    public RestSupportToolControllerTest() {
        super("/rest/tool/support/");
    }

    @Override
    protected SupportTool getInstance() {
        SupportTool tool = new SupportTool();
        tool.setId(counterId);
        tool.setName("name" + counterId);
        counterId++;
        return tool;
    }
}
