package com.toolsapp.service.extra.property;

import org.springframework.stereotype.Service;

@Service
public class PropertiesService {

    private final ToolTypeService toolTypeService;
    private final ProducerService producerService;

    public PropertiesService(ToolTypeService toolTypeService, ProducerService producerService) {
        this.toolTypeService = toolTypeService;
        this.producerService = producerService;
    }

    public ToolTypeService getGroupService() {
        return toolTypeService;
    }

    public ProducerService getProducerService() {
        return producerService;
    }
}
