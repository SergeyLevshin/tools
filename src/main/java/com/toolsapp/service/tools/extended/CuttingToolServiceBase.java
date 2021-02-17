package com.toolsapp.service.tools.extended;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.tools.CuttingTool;
import com.toolsapp.service.extra.worker.WorkerService;
import com.toolsapp.service.property.PropertiesService;
import com.toolsapp.service.tools.ToolService;
import org.springframework.stereotype.Service;

@Service
public class CuttingToolServiceBase extends ExtendedToolService<CuttingTool> {

    private CuttingToolServiceBase(ToolService<CuttingTool> toolService, PropertiesService propertiesService, WorkerService workerService) {
        super(toolService, propertiesService, workerService);
    }

    @Override
    protected void changeToolQuantity(Worker worker, CuttingTool tool, int quantity) {
        int tempQuantity = worker.getCuttingTools().get(tool);
        worker.getCuttingTools().put(tool, tempQuantity + quantity);
    }

}
