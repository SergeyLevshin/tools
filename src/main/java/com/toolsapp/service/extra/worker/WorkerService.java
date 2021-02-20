package com.toolsapp.service.extra.worker;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.tools.AbstractTool;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface WorkerService {
    List<Worker> findAll();
    Worker findById(long id);
    void save(Worker worker);

    Map<AbstractTool, Integer> getWorkerTools(long workerId);

    void removeToolFromWorker(long workerId, long toolId);
}
