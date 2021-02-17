package com.toolsapp.service.tools.extended;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.tools.Accessory;
import com.toolsapp.service.extra.worker.WorkerService;
import com.toolsapp.service.property.PropertiesService;
import com.toolsapp.service.tools.ToolService;
import org.springframework.stereotype.Service;

@Service
public class AccessoryServiceBase extends ExtendedToolService<Accessory> {

    private AccessoryServiceBase(ToolService<Accessory> toolService, PropertiesService propertiesService, WorkerService workerService) {
        super(toolService, propertiesService, workerService);
    }

    @Override
    protected void changeToolQuantity(Worker worker, Accessory tool, int quantity) {
        int tempQuantity = worker.getAccessories().get(tool);
        worker.getAccessories().put(tool, tempQuantity + quantity);
    }

}
