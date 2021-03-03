package com.toolsapp.rest.tool.common;

import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.service.tools.common.GeneralToolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/tool/general/")
public class RestGeneralToolController {

    private final GeneralToolService service;

    public RestGeneralToolController(GeneralToolService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AbstractTool>> findAll() {
        List<AbstractTool> tools = service.findAllSortByName();
        return tools != null && !tools.isEmpty()
                ? new ResponseEntity<>(tools, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id) {
        return service.deleteTool(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
