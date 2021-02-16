package com.toolsapp.service.extra.worker.extended;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.tools.CuttingTool;
import com.toolsapp.service.extra.worker.WorkerService;
import com.toolsapp.service.tools.ToolService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WorkerAndCuttingToolService
        extends AbstractWorkerAndToolService<CuttingTool> {

    public WorkerAndCuttingToolService(WorkerService workerService, ToolService<CuttingTool> toolService) {
        super(workerService, toolService);
    }

    @Override
    public Map<CuttingTool, Integer> workerTools(long workerId) {
        return findWorkerById(workerId).getCuttingTools();
    }

    @Override
    protected void removeToolFromWorkerToolMap(Worker worker, CuttingTool tool) {
        worker.getCuttingTools().remove(tool);
    }

}
