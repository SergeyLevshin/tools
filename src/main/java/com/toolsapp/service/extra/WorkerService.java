package com.toolsapp.service.extra;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.tools.CuttingTool;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface WorkerService {
    List<Worker> findAll();
    Optional<Worker> findById(long id);

    void setToolQuantityFromWorker(long workerId, long toolId, int quantity);

    void removeToolFromWorker(long workerId, long toolId, int quantity);

    Map<CuttingTool, Integer> workerTools(long id);
}
