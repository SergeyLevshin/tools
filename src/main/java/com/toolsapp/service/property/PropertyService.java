package com.toolsapp.service.property;

import com.toolsapp.domain.property.ToolProperty;

import java.util.List;
import java.util.Optional;

public interface PropertyService<E extends ToolProperty> {
    E save(E entity);
    List<E> findAll();
    Optional<E> findById(long id);
    void deleteById(long id);
}
