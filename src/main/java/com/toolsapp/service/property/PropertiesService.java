package com.toolsapp.service.property;

import com.toolsapp.models.property.Producer;
import com.toolsapp.models.property.ToolType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertiesService {

    private final ToolTypeService toolTypeService;
    private final ProducerService producerService;

    public PropertiesService(ToolTypeService toolTypeService, ProducerService producerService) {
        this.toolTypeService = toolTypeService;
        this.producerService = producerService;
    }

    public ToolTypeService getToolTypeService() {
        return toolTypeService;
    }

    public ProducerService getProducerService() {
        return producerService;
    }

    public void deleteToolTypeById(long id) {
        toolTypeService.deleteById(id);
    }

    public void deleteProducerById(long id) {
        producerService.deleteById(id);
    }

    public List<ToolType> findAllToolTypes() {
        return toolTypeService.findAll();
    }

    public List<Producer> findAllProducers() {
        return producerService.findAll();
    }

    public void saveToolType(ToolType toolType) {
        toolTypeService.save(toolType);
    }

    public void saveProducer(Producer producer) {
        producerService.save(producer);
    }
}
