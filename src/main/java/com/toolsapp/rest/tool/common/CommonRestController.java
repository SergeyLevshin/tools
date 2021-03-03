package com.toolsapp.rest.tool.common;

import com.toolsapp.domain.tools.AbstractTool;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface CommonRestController<E extends AbstractTool> {

    @GetMapping
    ResponseEntity<List<E>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<E> findById(long id);

    @PostMapping
    ResponseEntity<E> save(E entity);

    // DeleteById is not allowed.
    // The tool can be deleted only in GeneralToolController.
}
