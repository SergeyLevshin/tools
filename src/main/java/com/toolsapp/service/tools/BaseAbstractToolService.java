package com.toolsapp.service.tools;

import com.toolsapp.models.tools.AbstractTool;
import com.toolsapp.repository.tools.AbstractToolRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseAbstractToolService<E extends AbstractTool,
        R extends AbstractToolRepository<E>> implements ToolService<E>{

    private final R repository;

    public BaseAbstractToolService(R repository) {
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
