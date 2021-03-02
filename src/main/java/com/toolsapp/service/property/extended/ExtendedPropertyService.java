package com.toolsapp.service.property.extended;

import com.toolsapp.domain.property.Producer;
import com.toolsapp.domain.property.ToolFunction;
import com.toolsapp.domain.property.ToolProperty;
import com.toolsapp.service.property.ProducerService;
import com.toolsapp.service.property.ToolFunctionService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ExtendedPropertyService {

    private final ProducerService producerService;
    private final ToolFunctionService toolFunctionService;

    public ExtendedPropertyService(ProducerService producerService, ToolFunctionService toolFunctionService) {
        this.producerService = producerService;
        this.toolFunctionService = toolFunctionService;
    }

    public Optional<Producer> findProducerById(long id){
        return producerService.findById(id);
    }

    public Optional<ToolFunction> findToolFunctionById(long id) {
        return toolFunctionService.findById(id);
    }

    public boolean deleteToolFunctionById(long id) {
       return toolFunctionService.deleteById(id);
    }

    public boolean deleteProducerById(long id) {
        return producerService.deleteById(id);
    }

    public List<List<? extends ToolProperty>> findAllToolProperties() {
        return Arrays.asList(findAllToolFunctions(), findAllProducers());
    }

    public List<ToolFunction> findAllToolFunctions() {
        return toolFunctionService.findAllSortByName();
    }

    public List<Producer> findAllProducers() {
        return producerService.findAllSortByName();
    }


    public ToolFunction saveToolFunction(ToolFunction toolFunction) {
        toolFunctionService.save(toolFunction);
        return toolFunction;
    }

    public Producer saveProducer(Producer producer) {
        producerService.save(producer);
        return producer;
    }

}
