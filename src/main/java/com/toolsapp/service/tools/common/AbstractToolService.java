package com.toolsapp.service.tools.common;

import com.toolsapp.models.tools.AbstractTool;
import com.toolsapp.repository.tools.ToolRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractToolService<E extends AbstractTool,
        R extends ToolRepository<E>> implements ToolService<E>{

    private final R repository;

    public AbstractToolService(R repository) {
        this.repository = repository;
    }

    public void save(E entity) {
        repository.save(entity);
    }

    public List<E> findAll() {
        return (List<E>) repository.findAll();
    }

    public Optional<E> findById(long id) {
        return repository.findById(id);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
