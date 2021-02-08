package com.toolsapp.service.extra.property;

import com.toolsapp.models.extra.property.Property;

import java.util.List;
import java.util.Optional;

public interface PropertyService<E extends Property> {
    E save(E entity);
    List<E> findAll();
    Optional<E> findById(long id);
    void deleteById(long id);
}
