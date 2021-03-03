package com.toolsapp.rest.tool.common;

import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.service.CommonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

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
        List<E> tools = service.findAllSortByName();
        return tools != null && !tools.isEmpty()
                ? new ResponseEntity<>(tools, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<E> findById(@PathVariable("id") long id) {
        final Optional<E> tool = service.findById(id);
        return tool.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<E> save(E tool) {
        service.save(tool);
        return new ResponseEntity<>(tool, HttpStatus.CREATED);
    }
}
