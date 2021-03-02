package com.toolsapp.rest.tool.general;

import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.service.tools.common.GeneralToolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/tool")
public class RestGeneralController {

    private final GeneralToolService service;

    public RestGeneralController(GeneralToolService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<? extends AbstractTool> getAllTools() {
        return null;
    }
}
