package com.toolsapp.service.tools.extended;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.property.Producer;
import com.toolsapp.models.property.ToolType;
import com.toolsapp.models.tools.AbstractTool;
import com.toolsapp.service.extra.worker.WorkerService;
import com.toolsapp.service.property.PropertiesService;
import com.toolsapp.service.tools.ToolService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class ExtendedToolService<E extends AbstractTool> {

    private final ToolService<E> toolService;
    private final PropertiesService propertiesService;
    private final WorkerService workerService;

    protected ExtendedToolService(ToolService<E> toolService, PropertiesService propertiesService, WorkerService workerService) {
        this.toolService = toolService;
        this.propertiesService = propertiesService;
        this.workerService = workerService;
    }

    public List<E> findAllTools() {
        return toolService.findAll();
    }

    public List<Producer> findAllProducers() {
        return propertiesService.findAllProducers();
    }

    public List<ToolType> findAllToolTypes() {
        return propertiesService.findAllToolTypes();
    }

    public List<Worker> findAllWorkers() {
        return workerService.findAll();
    }

    public void saveTool(E tool) {
        toolService.save(tool);
    }

    public void deleteToolById(long id) {
        toolService.deleteById(id);
    }

    @Transactional
    public void giveToolToWorker(long toolId, int quantity, long workerId) {
        Worker worker = workerService.findById(workerId)
                .orElseThrow(NoSuchElementException::new);
        E tool = toolService.findById(toolId)
                .orElseThrow(NoSuchElementException::new);
        changeToolQuantity(worker, tool, quantity);
        workerService.save(worker);
    }

    protected abstract void changeToolQuantity(Worker worker, E tool, int quantity);
}
