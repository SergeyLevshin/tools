package com.toolsapp.service.tools.extended;

import com.toolsapp.models.tools.SupportTool;
import com.toolsapp.repository.tools.ToolRepository;
import com.toolsapp.service.extra.worker.WorkerService;
import com.toolsapp.service.property.extended.ExtendedPropertyService;
import org.springframework.stereotype.Service;

@Service
public class ExtendedSupportToolService extends ExtendedToolService<SupportTool> {

    protected ExtendedSupportToolService(ToolRepository<SupportTool> repository,
                                         ExtendedPropertyService propertiesService,
                                         WorkerService workerService) {
        super(repository, propertiesService, workerService);
    }
}
