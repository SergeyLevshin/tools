package com.toolsapp.service.tools.extended;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.tools.CuttingTool;
import com.toolsapp.repository.tools.AbstractToolRepository;
import com.toolsapp.service.extra.worker.WorkerService;
import com.toolsapp.service.property.PropertiesService;
import org.springframework.stereotype.Service;

@Service
public class ExtendedCuttingToolService extends ExtendedToolService<CuttingTool> {

    protected ExtendedCuttingToolService(AbstractToolRepository<CuttingTool> repository,
                                         PropertiesService propertiesService,
                                         WorkerService workerService) {
        super(repository, propertiesService, workerService);
    }

    @Override
    protected void changeToolQuantity(Worker worker, CuttingTool tool, int quantity) {
        int tempQuantity = worker.getCuttingTools().get(tool);
        worker.getCuttingTools().put(tool, tempQuantity + quantity);
    }

}
