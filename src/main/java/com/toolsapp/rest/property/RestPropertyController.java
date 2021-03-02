package com.toolsapp.rest.property;

import com.toolsapp.domain.property.Producer;
import com.toolsapp.domain.property.ToolFunction;
import com.toolsapp.domain.property.ToolProperty;
import com.toolsapp.service.property.extended.ExtendedPropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/property")
public class RestPropertyController {

    private final ExtendedPropertyService service;

    public RestPropertyController(ExtendedPropertyService service) {
        this.service = service;
    }

    @GetMapping("/allToolProperties")
    public ResponseEntity<List<List<? extends ToolProperty>>> getAllToolProperties() {
        List<List<? extends ToolProperty>> allToolProperties = service.findAllToolProperties();
        return allToolProperties !=null && !allToolProperties.isEmpty()
                ? new ResponseEntity<>(allToolProperties, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("addProducer")
    public ResponseEntity<Producer> addProducer(@Valid @RequestBody Producer producer) {
        service.saveProducer(producer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("addToolFunction")
    public ResponseEntity<ToolFunction> addToolFunction(@Valid @RequestBody ToolFunction toolFunction) {
        service.saveToolFunction(toolFunction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteProducer/{id}")
    public ResponseEntity<?> deleteProducer(@PathVariable("id") long id) {
        return service.deleteProducerById(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/deleteToolFunction/{id}")
    public ResponseEntity<?> deleteToolFunction(@PathVariable("id") long id) {
        return service.deleteToolFunctionById(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
