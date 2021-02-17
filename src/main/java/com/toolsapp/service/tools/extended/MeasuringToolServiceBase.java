package com.toolsapp.service.tools.extended;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.tools.MeasuringTool;
import com.toolsapp.service.extra.worker.WorkerService;
import com.toolsapp.service.property.PropertiesService;
import com.toolsapp.service.tools.ToolService;
import org.springframework.stereotype.Service;

@Service
public class MeasuringToolServiceBase extends ExtendedToolService<MeasuringTool> {

    private MeasuringToolServiceBase(ToolService<MeasuringTool> toolService, PropertiesService propertiesService, WorkerService workerService) {
        super(toolService, propertiesService, workerService);
    }

    @Override
    protected void changeToolQuantity(Worker worker, MeasuringTool tool, int quantity) {
        int tempQuantity = worker.getMeasuringTools().get(tool);
        worker.getMeasuringTools().put(tool, tempQuantity + quantity);
    }

}
