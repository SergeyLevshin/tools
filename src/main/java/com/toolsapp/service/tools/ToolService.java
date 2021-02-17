package com.toolsapp.service.tools;

import com.toolsapp.models.tools.AbstractTool;
import com.toolsapp.models.tools.CuttingTool;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ToolService<E extends AbstractTool> {

    List<E> findAll();
    Optional<E> findById(long id);
    void deleteById(long id);
    void save(E tool);
}
