package com.toolsapp.service.extra.property;

import org.springframework.stereotype.Service;

@Service
public class PropertiesService {

    private final GroupService groupService;
    private final ProducerService producerService;

    public PropertiesService(GroupService groupService, ProducerService producerService) {
        this.groupService = groupService;
        this.producerService = producerService;
    }

    public GroupService getGroupService() {
        return groupService;
    }

    public ProducerService getProducerService() {
        return producerService;
    }
}
