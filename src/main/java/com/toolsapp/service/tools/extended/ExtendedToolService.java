package com.toolsapp.service.tools.extended;

import com.toolsapp.domain.extra.Worker;
import com.toolsapp.domain.property.Producer;
import com.toolsapp.domain.property.ToolFunction;
import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.repository.tools.ToolRepository;
import com.toolsapp.service.extra.worker.WorkerService;
import com.toolsapp.service.property.extended.ExtendedPropertyService;
import com.toolsapp.service.tools.common.AbstractToolService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class ExtendedToolService<E extends AbstractTool>
        extends AbstractToolService<E, ToolRepository<E>> {

    private final WorkerService workerService;
    private final ExtendedPropertyService propertyService;

    protected ExtendedToolService(ToolRepository<E> repository,
                                  WorkerService workerService,
                                  ExtendedPropertyService propertyService) {
        super(repository);
        this.workerService = workerService;
        this.propertyService = propertyService;
    }

    public void saveTool(E tool) {
        save(tool);
    }

    public List<E> findAllTools() {
        return findAll();
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
        saveTool(tool);
        workerService.save(worker);
    }

    @Transactional
    private void changeToolQuantity(Worker worker, E tool, int quantity) {
        if (tool.getQuantity() >= quantity) {
            tool.setQuantity(tool.getQuantity() - quantity);
            worker.increaseToolQuantity(tool, quantity);
        }
    }

    public List<Worker> findAllWorkers() {
        return workerService.findAll();
    }

    public List<ToolFunction> findAllToolFunctions() {
        return propertyService.findAllToolFunctions();
    }

    public List<Producer> findAllProducers()  {
        return propertyService.findAllProducers();
    }
}
