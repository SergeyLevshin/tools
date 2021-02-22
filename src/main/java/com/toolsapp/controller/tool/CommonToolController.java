package com.toolsapp.controller.tool;

import com.toolsapp.domain.tools.AbstractTool;
import org.springframework.ui.Model;

import javax.validation.Valid;

public interface CommonToolController<E extends AbstractTool> {

    String give(Model model);

    String giveTool(@Valid long toolId,
                           int quantity, @Valid long workerId);

}
