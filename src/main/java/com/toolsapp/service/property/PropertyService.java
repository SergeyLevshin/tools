package com.toolsapp.service.property;

import com.toolsapp.models.property.Property;

import java.util.List;
import java.util.Optional;

public interface PropertyService<E extends Property> {
    E save(E entity);
    List<E> findAll();
    Optional<E> findById(long id);
    void deleteById(long id);
}
