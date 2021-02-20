package com.toolsapp.service.tools.extended;

import com.toolsapp.models.tools.MeasuringTool;
import com.toolsapp.repository.tools.ToolRepository;
import com.toolsapp.service.extra.worker.WorkerService;
import com.toolsapp.service.property.extended.ExtendedPropertyService;
import org.springframework.stereotype.Service;

@Service
public class ExtendedMeasuringToolService extends ExtendedToolService<MeasuringTool> {

    protected ExtendedMeasuringToolService(ToolRepository<MeasuringTool> repository,
                                           ExtendedPropertyService propertiesService,
                                           WorkerService workerService) {
        super(repository, propertiesService, workerService);
    }
}
