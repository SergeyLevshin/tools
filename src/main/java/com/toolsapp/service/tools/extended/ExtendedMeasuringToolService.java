package com.toolsapp.service.tools.extended;

import com.toolsapp.domain.tools.MeasuringTool;
import com.toolsapp.repository.tools.ToolRepository;
import com.toolsapp.service.extra.worker.WorkerService;
import com.toolsapp.service.property.extended.ExtendedPropertyService;
import org.springframework.stereotype.Service;

@Service
public class ExtendedMeasuringToolService extends ExtendedToolService<MeasuringTool> {

    protected ExtendedMeasuringToolService(ToolRepository<MeasuringTool> repository,
                                           WorkerService workerService,
                                           ExtendedPropertyService propertyService) {
        super(repository, workerService, propertyService);
    }
}
