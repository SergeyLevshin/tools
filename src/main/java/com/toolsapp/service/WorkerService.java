package com.toolsapp.service;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.instrument.CuttingTool;

import java.util.List;
import java.util.Map;

public interface WorkerService {
    List<Worker> findAll();
    Worker findById(long id);
    Map<CuttingTool, Integer> workerTools(long id);
    void removeTool(long workerId, long id, int quantity);
}
