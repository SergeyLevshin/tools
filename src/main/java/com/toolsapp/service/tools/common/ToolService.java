package com.toolsapp.service.tools.common;

import com.toolsapp.domain.tools.AbstractTool;

import java.util.List;
import java.util.Optional;

public interface ToolService<E extends AbstractTool> {

    List<E> findAll();
    Optional<E> findById(long id);
    void deleteById(long id);
    void save(E tool);
}
