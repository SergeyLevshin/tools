package com.toolsapp.service.tools;

import com.toolsapp.models.tools.AbstractTool;

import java.util.List;
import java.util.Optional;

public interface ToolService<E extends AbstractTool> {
    E save(E entity);
    List<E> findAll();
    Optional<E> findById(long id);
    void deleteById(long id);
}
