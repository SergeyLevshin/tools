package com.toolsapp.service.combined;

import com.toolsapp.models.tools.CuttingTool;
import com.toolsapp.service.extra.WorkerService;
import com.toolsapp.service.extra.WorkerServiceImpl;
import com.toolsapp.service.tools.CuttingToolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class CuttingToolAndWorkerService {

    private final CuttingToolService cuttingToolService;
    private final WorkerServiceImpl workerService;

    public CuttingToolAndWorkerService(CuttingToolService cuttingToolService, WorkerServiceImpl workerService) {
        this.cuttingToolService = cuttingToolService;
        this.workerService = workerService;
    }

    public CuttingToolService getCuttingToolService() {
        return cuttingToolService;
    }

    public WorkerService getWorkerService() {
        return workerService;
    }

    public void giveToolToWorker(long toolId, int quantity, long workerId) {
        if (cuttingToolService.checkToolQuantityFromTool(toolId, quantity))
            workerService.setToolQuantityFromWorker(workerId, toolId, quantity);
    }

    @Transactional
    public void removeTool(long workerId, long toolId, int quantity) {
        workerService.removeToolFromWorker(workerId, toolId, quantity);
        cuttingToolService.changeQuantityInUse(toolId, quantity);
    }

    @Transactional
    public Map<CuttingTool, Integer> workerTools(long id) {
        Map<Long, Integer> toolIdMap =
                workerService.findById(id).orElseThrow().getCuttingTools();
        return cuttingToolService.createToolMap(toolIdMap);
    }
}
