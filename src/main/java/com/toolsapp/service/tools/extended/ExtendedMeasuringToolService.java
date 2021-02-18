package com.toolsapp.service.tools.extended;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.tools.MeasuringTool;
import com.toolsapp.repository.tools.AbstractToolRepository;
import com.toolsapp.service.extra.worker.WorkerService;
import com.toolsapp.service.property.PropertiesService;
import org.springframework.stereotype.Service;

@Service
public class ExtendedMeasuringToolService extends ExtendedToolService<MeasuringTool> {

    protected ExtendedMeasuringToolService(AbstractToolRepository<MeasuringTool> repository,
                                           PropertiesService propertiesService,
                                           WorkerService workerService) {
        super(repository, propertiesService, workerService);
    }

    @Override
    protected void changeToolQuantity(Worker worker, MeasuringTool tool, int quantity) {
        int tempQuantity = worker.getMeasuringTools().get(tool);
        worker.getMeasuringTools().put(tool, tempQuantity + quantity);
    }

}
