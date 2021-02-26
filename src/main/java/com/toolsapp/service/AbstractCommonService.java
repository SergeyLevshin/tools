package com.toolsapp.service;

import com.toolsapp.repository.CommonRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractCommonService
        <E, R extends CommonRepository<E>> implements CommonService<E> {

    protected final R repository;

    protected AbstractCommonService(R repository) {
        this.repository = repository;
    }

    @Override
    public List<E> showAllSortByName() {
        return repository.findAllByOrderByNameAsc();
    }

    public E save(E entity) {
        repository.save(entity);
        return entity;
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
