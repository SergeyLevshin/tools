package com.toolsapp.rest.property;

import com.toolsapp.domain.extra.Product;
import com.toolsapp.domain.property.Producer;
import com.toolsapp.domain.property.ToolFunction;
import com.toolsapp.domain.property.ToolProperty;
import com.toolsapp.service.property.extended.ExtendedPropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/property")
public class RestPropertyController {

    private final ExtendedPropertyService service;

    public RestPropertyController(ExtendedPropertyService service) {
        this.service = service;
    }

    @GetMapping("/allToolProperties")
    public ResponseEntity<List<ToolProperty>> getAllToolProperties() {
        final List<ToolProperty> properties = service.findAllToolProperties();
        return properties !=null && !properties.isEmpty()
                ? new ResponseEntity<>(properties, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/producer/{id}")
    public ResponseEntity<Producer> getSingleProducer(@PathVariable("id") long id) {
        final Optional<Producer> producer = service.findProducerById(id);
        return producer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/toolFunction/{id}")
    public ResponseEntity<ToolFunction> getSingleToolFunction(@PathVariable("id") long id) {
        final Optional<ToolFunction> toolFunction = service.findToolFunctionById(id);
        return toolFunction.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
