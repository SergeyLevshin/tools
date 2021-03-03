package com.toolsapp.rest.tool.extended;

import com.toolsapp.domain.extra.Worker;
import com.toolsapp.domain.property.ToolProperty;
import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.service.tools.extended.ExtendedToolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public abstract class AbstractExtendedRestToolController<E extends AbstractTool,
        S extends ExtendedToolService<E>>
        implements CommonExtendedRestToolController<E>{

    private final S service;

    protected AbstractExtendedRestToolController(S service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Worker> giveTool(long toolId, int quantity, long workerId) {
        return service.giveToolToWorker(toolId, quantity, workerId)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Override
    public ResponseEntity<E> addToolProperty(@RequestBody @Valid E tool,
                                             @RequestBody @Valid ToolProperty property) {
        addToolProperty(tool, property);
        return new ResponseEntity<>(tool, HttpStatus.OK);
    }
}
