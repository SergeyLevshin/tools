package com.toolsapp.rest.tool;

import com.toolsapp.domain.tools.AbstractTool;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface CommonRestController<E extends AbstractTool> {

    @PostMapping
    ResponseEntity<List<E>> getAll();
}
