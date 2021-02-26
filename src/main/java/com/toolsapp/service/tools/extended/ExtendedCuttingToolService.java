package com.toolsapp.service.tools.extended;

import com.toolsapp.domain.tools.CuttingTool;
import com.toolsapp.repository.CommonRepository;
import com.toolsapp.service.extra.worker.WorkerService;
import com.toolsapp.service.property.extended.ExtendedPropertyService;
import org.springframework.stereotype.Service;

@Service
public class ExtendedCuttingToolService extends ExtendedToolService<CuttingTool> {

    protected ExtendedCuttingToolService(CommonRepository<CuttingTool> repository,
                                         WorkerService workerService,
                                         ExtendedPropertyService propertyService) {
        super(repository, workerService, propertyService);
    }
}
