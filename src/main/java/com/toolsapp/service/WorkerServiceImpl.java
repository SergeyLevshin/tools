package com.toolsapp.service;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.instrument.CuttingTool;
import com.toolsapp.repository.CuttingToolsRepository;
import com.toolsapp.repository.WorkerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;
    private final CuttingToolsRepository toolsRepository;

    public WorkerServiceImpl(WorkerRepository workerRepository, CuttingToolsRepository toolsRepository) {
        this.workerRepository = workerRepository;
        this.toolsRepository = toolsRepository;
    }

    @Override
    public List<Worker> findAll() {
        return (List<Worker>) workerRepository.findAll();
    }

    @Override
    public Worker findById(long id) {
        return workerRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Map<CuttingTool, Integer> workerTools(long id) {
        Map<CuttingTool, Integer> resultMap = new HashMap<>();
        Map<Long, Integer> toolIdMap =
                workerRepository.findById(id).orElse(null).getCuttingTools();
        for (Map.Entry<Long, Integer> tool : toolIdMap.entrySet()) {
            CuttingTool newTool = toolsRepository.findById(tool.getKey()).orElseThrow();
            resultMap.put(newTool, tool.getValue());
        }
        return resultMap;
    }

    @Transactional
    @Override
    public void removeTool(long workerId, long toolId, int quantity) {
        Worker worker = workerRepository.findById(workerId).orElseThrow();
        int newQuantity = worker.getCuttingTools().get(toolId) - quantity;
        if (newQuantity >= 0) {
            worker.getCuttingTools().put(toolId, newQuantity);
            workerRepository.save(worker);
        }
    }


}
