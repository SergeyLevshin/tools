package com.toolsapp.rest.tool;

import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.service.CommonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class AbstractRestToolController<E extends AbstractTool, S extends CommonService<E>>
        implements CommonRestController<E> {

    private final S service;

    public AbstractRestToolController(S service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<E>> getAll() {
        List<E> entities = service.findAllSortByName();
        return entities != null && !entities.isEmpty()
                ? new ResponseEntity<>(entities, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
