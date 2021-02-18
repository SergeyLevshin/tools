package com.toolsapp.service.extra.worker.extended;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.tools.Accessory;
import com.toolsapp.service.extra.worker.WorkerService;
import com.toolsapp.service.tools.ToolService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WorkerAndAccessoryService
        extends AbstractWorkerAndToolService<Accessory> {

    public WorkerAndAccessoryService(WorkerService workerService,
                                     ToolService<Accessory> toolService) {
        super(workerService, toolService);
    }

    @Override
    public Map<Accessory, Integer> getWorkerTools(long workerId) {
        return findWorkerById(workerId).getAccessories();
    }

    @Override
    protected void removeToolFromWorkerToolMap(Worker worker, Accessory tool) {
        worker.getAccessories().remove(tool);
    }
}
