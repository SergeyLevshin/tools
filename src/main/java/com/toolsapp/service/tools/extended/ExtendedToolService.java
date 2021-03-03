package com.toolsapp.service.tools.extended;

import com.toolsapp.domain.extra.Worker;
import com.toolsapp.domain.property.Producer;
import com.toolsapp.domain.property.ToolFunction;
import com.toolsapp.domain.property.ToolProperty;
import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.repository.CommonRepository;
import com.toolsapp.service.AbstractCommonService;
import com.toolsapp.service.extra.worker.WorkerService;
import com.toolsapp.service.property.extended.ExtendedPropertyService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    // Give tool to worker if tool have enough quantity.
    @Transactional
    public boolean giveToolToWorker(long toolId, int quantity, long workerId) {
        if (workerService.findById(workerId).isPresent()
                && findById(toolId).isPresent()) {
            Worker worker = workerService.findById(workerId).get();
            E tool = findById(toolId).get();
            if (changeToolQuantity(tool, quantity, worker)) {
                save(tool);
                workerService.save(worker);
                return true;
            }
        }
        return false;
    }

    @Transactional
    private boolean changeToolQuantity(E tool, int quantity, Worker worker) {
        if (quantity > 0
                && tool.getQuantity() >= quantity) {
            tool.setQuantity(tool.getQuantity() - quantity);
            worker.increaseToolQuantity(tool, quantity);
            return true;
        }
        return false;
    }

    @Transactional
    public E addToolProperty(E tool, ToolProperty property) {
        if (property instanceof Producer) {
            tool.setProducer((Producer) property);
        }
        if (property instanceof ToolFunction) {
            tool.setToolFunction((ToolFunction) property);
        }
        return tool;
    }

    public List<Worker> findAllWorkers() {
        return workerService.findAllSortByName();
    }

    public List<ToolFunction> findAllToolFunctions() {
        return propertyService.findAllToolFunctions();
    }

    public List<Producer> findAllProducers()  {
        return propertyService.findAllProducers();
    }
}
