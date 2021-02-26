package com.toolsapp.service;

import java.util.List;
import java.util.Optional;

public interface CommonService<E> {
    List<E> findAll();
    Optional<E> findById(long id);
    void deleteById(long id);
    E save(E tool);
    List<E> findAllSortByName();
}
