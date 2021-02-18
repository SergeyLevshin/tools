package com.toolsapp.service.tools;

import com.toolsapp.models.tools.AbstractTool;

import java.util.List;
import java.util.Optional;

public interface ToolService<E extends AbstractTool> {

    List<E> findAll();
    Optional<E> findById(long id);
    void deleteById(long id);
    void save(E tool);
}
