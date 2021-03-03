package com.toolsapp.rest.tool.extended;

import com.toolsapp.domain.extra.Worker;
import com.toolsapp.domain.property.ToolProperty;
import com.toolsapp.domain.tools.AbstractTool;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface CommonExtendedRestToolController<E extends AbstractTool> {

    @PatchMapping("/giveToWorker{id}")
    ResponseEntity<Worker> giveTool(long toolId, int quantity, long workerId);

    @PatchMapping("/addProperty")
    ResponseEntity<E> addToolProperty(@RequestBody @Valid E tool,
                                      @RequestBody @Valid ToolProperty property);

}
