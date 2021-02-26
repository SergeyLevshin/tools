package com.toolsapp.service.tools.extended;

import com.toolsapp.domain.extra.Worker;
import com.toolsapp.domain.property.Producer;
import com.toolsapp.domain.property.ToolFunction;
import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.repository.CommonRepository;
import com.toolsapp.service.AbstractCommonService;
import com.toolsapp.service.CommonService;
import com.toolsapp.service.extra.worker.WorkerService;
import com.toolsapp.service.property.extended.ExtendedPropertyService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class ExtendedToolService<E extends AbstractTool>
        extends AbstractCommonService<E, CommonRepository<E>> {

    private final WorkerService workerService;
    private final ExtendedPropertyService propertyService;

    protected ExtendedToolService(CommonRepository<E> repository,
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
        if (workerService.findById(workerId).isPresent()) {
            Worker worker = workerService.findById(workerId).get();
            E tool = findById(toolId)
                    .orElseThrow(NoSuchElementException::new);
            changeToolQuantity(tool, quantity, worker);
            saveTool(tool);
            workerService.save(worker);
        }
    }

    @Transactional
    private void changeToolQuantity(E tool, int quantity, Worker worker) {
        if (quantity > 0
                && tool.getQuantity() >= quantity) {
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
