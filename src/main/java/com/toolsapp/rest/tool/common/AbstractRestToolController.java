package com.toolsapp.rest.tool.common;

import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.service.CommonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public abstract class AbstractRestToolController<E extends AbstractTool,
        S extends CommonService<E>>
        implements CommonRestController<E> {

    private final S service;

    public AbstractRestToolController(S service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<E>> findAll() {
        List<E> entities = service.findAllSortByName();
        return entities != null && !entities.isEmpty()
                ? new ResponseEntity<>(entities, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<E> findById(long id) {
        final Optional<E> product = service.findById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<E> save(E entity) {
        service.save(entity);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }
}
