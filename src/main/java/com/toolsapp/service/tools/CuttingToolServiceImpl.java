package com.toolsapp.service.tools;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.tools.CuttingTool;
import com.toolsapp.repository.tools.CuttingToolsRepository;
import com.toolsapp.repository.extra.WorkerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CuttingToolServiceImpl implements CuttingToolService {

    private final CuttingToolsRepository cuttingToolsRepo;
    private final WorkerRepository workerRepo;

    public CuttingToolServiceImpl(CuttingToolsRepository cuttingToolsRepo, WorkerRepository workerRepo) {
        this.cuttingToolsRepo = cuttingToolsRepo;
        this.workerRepo = workerRepo;
    }

    @Override
    public List<CuttingTool> findAll() {
        return (List<CuttingTool>) cuttingToolsRepo.findAll();
    }

    @Override
    public CuttingTool findById(long id) {
        return cuttingToolsRepo.findById(id).orElse(null);
    }

    @Override
    public void save(CuttingTool cuttingTool) {
        cuttingToolsRepo.save(cuttingTool);
    }

    @Override
    @Transactional
    public void giveToolToWorker(long toolId, int quantity, long workerId) {
        if (checkToolQuantityFromTool(toolId, quantity))
            setToolQuantityFromWorker(workerId, toolId, quantity);

    }

    @Transactional
    private boolean checkToolQuantityFromTool(long toolId, int quantity) {
        CuttingTool tool = cuttingToolsRepo.findById(toolId).orElseThrow();
        if (tool.getQuantity() >= quantity) {
            tool.setQuantity(tool.getQuantity() - quantity);
            tool.setQuantityInUse(tool.getQuantityInUse() + quantity);
            cuttingToolsRepo.save(tool);
            return true;
        }
        return false;
    }

    @Transactional
    private void setToolQuantityFromWorker(long workerId, long toolId, int quantity){
        Worker worker = workerRepo.findById(workerId).orElseThrow();
        int tempQuantity = worker.getCuttingTools().getOrDefault(toolId, 0);
        worker.getCuttingTools().put(toolId, tempQuantity + quantity);
        workerRepo.save(worker);
    }

    @Transactional
    @Override
    public void delete(long id) {
        int quantity = cuttingToolsRepo.findById(id).orElseThrow().getQuantity();
        int quantityInUse = cuttingToolsRepo.findById(id).orElseThrow().getQuantityInUse();
        if (quantity == 0 && quantityInUse == 0) {
            cuttingToolsRepo.deleteById(id);
        }
    }

}
