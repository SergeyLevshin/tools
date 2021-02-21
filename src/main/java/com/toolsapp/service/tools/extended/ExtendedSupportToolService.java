package com.toolsapp.service.tools.extended;

import com.toolsapp.domain.tools.SupportTool;
import com.toolsapp.repository.tools.ToolRepository;
import com.toolsapp.service.extra.worker.WorkerService;
import com.toolsapp.service.property.extended.ExtendedPropertyService;
import org.springframework.stereotype.Service;

@Service
public class ExtendedSupportToolService extends ExtendedToolService<SupportTool> {

    protected ExtendedSupportToolService(ToolRepository<SupportTool> repository,
                                         WorkerService workerService,
                                         ExtendedPropertyService propertyService) {
        super(repository, workerService, propertyService);
    }
}
