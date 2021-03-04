package com.toolsapp.rest.tool.extended;

import com.toolsapp.domain.extra.Worker;
import com.toolsapp.domain.property.ToolProperty;
import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.service.tools.extended.ExtendedGeneralToolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest/tool/general/")
public class ExtendedRestToolController{

    private final ExtendedGeneralToolService service;

    protected ExtendedRestToolController(ExtendedGeneralToolService service) {
        this.service = service;
    }

    @PatchMapping("/giveToWorker/{workerId}")
    public ResponseEntity<Worker> giveTool(@Valid @RequestParam long toolId,
                                           @Valid @RequestParam int quantity,
                                           @PathVariable("workerId") long workerId) {
        return service.giveToolToWorker(toolId, quantity, workerId)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PatchMapping("/addToolProperty")
    public ResponseEntity<AbstractTool> addToolProperty(@RequestBody @Valid AbstractTool tool,
                                             @RequestBody @Valid ToolProperty property) {
        service.addToolProperty(tool, property);
        return new ResponseEntity<>(tool, HttpStatus.OK);
    }
}
