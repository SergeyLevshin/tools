package com.toolsapp.controller.tool;

import com.toolsapp.domain.tools.AbstractTool;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface CommonToolController<E extends AbstractTool> {

    String give(Model model);

    String giveTool(long toolId, int quantity, long workerId);

    String addTool(E tool, Model model);

    String addingTool(E tool, BindingResult bindingResult, Model model);
}
