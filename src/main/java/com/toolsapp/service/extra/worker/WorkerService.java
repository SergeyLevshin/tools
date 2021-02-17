package com.toolsapp.service.extra.worker;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.tools.CuttingTool;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface WorkerService {
    List<Worker> findAll();
    Optional<Worker> findById(long id);
    void save(Worker worker);
}
