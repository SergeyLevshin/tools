package com.toolsapp.service.extra.worker;

import com.toolsapp.domain.extra.Worker;
import com.toolsapp.domain.tools.AbstractTool;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface WorkerService {
    List<Worker> findAll();
    Optional<Worker> findById(long id);
    Worker save(Worker worker);

    Map<AbstractTool, Integer> getWorkerTools(long workerId);

    void removeToolFromWorker(long workerId, long toolId, int quantity);
}
