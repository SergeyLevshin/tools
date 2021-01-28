package com.toolsapp.service;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.instrument.CuttingTool;
import com.toolsapp.repository.CuttingToolsRepository;
import com.toolsapp.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InstrumentServiceImpl implements InstrumentService {

    private final CuttingToolsRepository cuttingToolsRepo;
    private final WorkerRepository workerRepo;

    @Autowired
    public InstrumentServiceImpl(CuttingToolsRepository cuttingToolsRepo, WorkerRepository workerRepo) {
        this.cuttingToolsRepo = cuttingToolsRepo;
        this.workerRepo = workerRepo;
    }

    @Override
    public List<CuttingTool> findAll() {
        return cuttingToolsRepo.findAll();
    }

    @Override
    public void save(CuttingTool cuttingTool) {
        cuttingToolsRepo.save(cuttingTool);
    }

    @Override
    @Transactional
    public void giveToolToWorker(CuttingTool tool, int quantity, Worker worker) {
        if (tool.getQuantity() < quantity)
            return;
        tool.setQuantity(tool.getQuantity() - quantity);
        int tempQuantity = worker.getCuttingTools().get(tool);
        worker.getCuttingTools().put(tool, tempQuantity + quantity);
        cuttingToolsRepo.save(tool);
        workerRepo.save(worker);
    }

    @Override
    public void delete(CuttingTool tool) {
        cuttingToolsRepo.delete(tool);
    }

}
