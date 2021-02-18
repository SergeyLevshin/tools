package com.toolsapp.service.tools.extended;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.tools.Accessory;
import com.toolsapp.repository.tools.AbstractToolRepository;
import com.toolsapp.service.extra.worker.WorkerService;
import com.toolsapp.service.property.PropertiesService;
import org.springframework.stereotype.Service;

@Service
public class ExtendedAccessoryService extends ExtendedToolService<Accessory> {

    protected ExtendedAccessoryService(AbstractToolRepository<Accessory> repository,
                                       PropertiesService propertiesService,
                                       WorkerService workerService) {
        super(repository, propertiesService, workerService);
    }

    @Override
    protected void changeToolQuantity(Worker worker, Accessory tool, int quantity) {
        int tempQuantity = worker.getAccessories().get(tool);
        worker.getAccessories().put(tool, tempQuantity + quantity);
    }

}
