package com.toolsapp.service.extra.worker.extended;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.tools.AbstractTool;
import com.toolsapp.service.extra.worker.WorkerService;
import com.toolsapp.service.tools.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public abstract class AbstractWorkerAndToolService<E extends AbstractTool> {

    private final WorkerService workerService;
    private final ToolService<E> toolService;

    public AbstractWorkerAndToolService(WorkerService workerService, ToolService<E> toolService) {
        this.workerService = workerService;
        this.toolService = toolService;
    }

    public List<Worker> findAllWorkers() {
        return workerService.findAll();
    }

    public Worker findWorkerById(long id) {
        return workerService.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public void removeToolFromWorker(long workerId, long toolId) {
        E tool = findToolById(toolId);
        Worker worker = findWorkerById(workerId);
        Map<E, Integer> workerToolsMap = getWorkerTools(workerId);
        if (workerToolsMap.containsKey(tool)
                && workerToolsMap.get(tool) == 0) {
            removeToolFromWorkerToolMap(worker, tool);
            workerService.save(worker);
        }
        //TODO else throw something
    }

    public abstract Map<E, Integer> getWorkerTools(long workerId);

    protected abstract void removeToolFromWorkerToolMap(Worker worker, E tool);

    private E findToolById(long toolId) {
        return toolService.findById(toolId).orElseThrow(NoSuchElementException::new);
    }
}
