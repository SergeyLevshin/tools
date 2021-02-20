package com.toolsapp.service.tools.extended;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.property.Producer;
import com.toolsapp.models.property.ToolFunction;
import com.toolsapp.models.tools.AbstractTool;
import com.toolsapp.repository.tools.ToolRepository;
import com.toolsapp.service.extra.worker.WorkerService;
import com.toolsapp.service.property.extended.ExtendedPropertyService;
import com.toolsapp.service.tools.common.AbstractToolService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class ExtendedToolService<E extends AbstractTool>
        extends AbstractToolService<E, ToolRepository<E>> {

    private final ExtendedPropertyService propertiesService;
    private final WorkerService workerService;

    protected ExtendedToolService(ToolRepository<E> repository,
                                  ExtendedPropertyService propertiesService,
                                  WorkerService workerService) {
        super(repository);
        this.propertiesService = propertiesService;
        this.workerService = workerService;
    }

    public List<E> findAllTools() {
        return findAll();
    }

    public List<Producer> findAllProducers() {
        return propertiesService.findAllProducers();
    }

    public List<ToolFunction> findAllToolFunctions() {
        return propertiesService.findAllToolFunctions();
    }

    public List<Worker> findAllWorkers() {
        return workerService.findAll();
    }

    public void saveTool(E tool) {
        save(tool);
    }

    public void deleteToolById(long id) {
        deleteById(id);
    }

    @Transactional
    public void giveToolToWorker(long toolId, int quantity, long workerId) {
        Worker worker = workerService.findById(workerId);
        E tool = findById(toolId)
                .orElseThrow(NoSuchElementException::new);
        changeToolQuantity(worker, tool, quantity);
        workerService.save(worker);
    }

    protected void changeToolQuantity(Worker worker, E tool, int quantity) {

    }
}
