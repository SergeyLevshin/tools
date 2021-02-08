package com.toolsapp.service.extra.property;

import com.toolsapp.models.extra.property.Property;
import com.toolsapp.repository.extra.property.PropertyRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractPropertyService<E extends Property,
        R extends PropertyRepository<E>> implements PropertyService<E> {

    protected final R repository;

    protected AbstractPropertyService(R repository) {
        this.repository = repository;
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
