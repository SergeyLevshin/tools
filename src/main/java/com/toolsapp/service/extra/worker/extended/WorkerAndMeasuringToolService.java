package com.toolsapp.service.extra.worker.extended;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.tools.MeasuringTool;
import com.toolsapp.service.extra.worker.WorkerService;
import com.toolsapp.service.tools.ToolService;

import java.util.Map;

public class WorkerAndMeasuringToolService
        extends AbstractWorkerAndToolService<MeasuringTool> {

    public WorkerAndMeasuringToolService(WorkerService workerService, ToolService<MeasuringTool> toolService) {
        super(workerService, toolService);
    }

    @Override
    public Map<MeasuringTool, Integer> getWorkerTools(long workerId) {
        return findWorkerById(workerId).getMeasuringTools();
    }

    @Override
    protected void removeToolFromWorkerToolMap(Worker worker, MeasuringTool tool) {
        worker.getMeasuringTools().remove(tool);
    }
}
