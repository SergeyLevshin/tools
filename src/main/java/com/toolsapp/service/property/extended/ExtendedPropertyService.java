package com.toolsapp.service.property.extended;

import com.toolsapp.domain.property.Producer;
import com.toolsapp.domain.property.ToolFunction;
import com.toolsapp.service.property.ProducerService;
import com.toolsapp.service.property.ToolFunctionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtendedPropertyService {

    private final ProducerService producerService;
    private final ToolFunctionService toolFunctionService;

    public ExtendedPropertyService(ProducerService producerService, ToolFunctionService toolFunctionService) {
        this.producerService = producerService;
        this.toolFunctionService = toolFunctionService;
    }

    public void deleteToolFunctionById(long id) {
       toolFunctionService.deleteById(id);
    }

    public void deleteProducerById(long id) {
        producerService.deleteById(id);
    }


    public List<ToolFunction> findAllToolFunctions() {
        return toolFunctionService.findAll();
    }

    public List<Producer> findAllProducers() {
        return producerService.findAll();
    }


    public void saveToolFunction(ToolFunction toolFunction) {
        toolFunctionService.save(toolFunction);
    }

    public void saveProducer(Producer producer) {
        producerService.save(producer);
    }

}
