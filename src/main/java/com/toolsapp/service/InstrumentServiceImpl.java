package com.toolsapp.service;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.instrument.CuttingTool;
import com.toolsapp.repository.CuttingToolsRepository;
import com.toolsapp.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
        return (List<CuttingTool>) cuttingToolsRepo.findAll();
    }

    @Override
    public void save(CuttingTool cuttingTool) {
        cuttingToolsRepo.save(cuttingTool);
    }

    @Override
    @Transactional
    public void giveToolToWorker(long toolId, int quantity, long workerId) {
        CuttingTool tool = cuttingToolsRepo.findById(toolId).orElse(new CuttingTool());
        Worker worker = workerRepo.findById(workerId).orElse(new Worker());

        if (tool.getQuantity() < quantity)
            return;
        tool.setQuantity(tool.getQuantity() - quantity);
        int tempQuantity = worker.getCuttingTools().get(tool);
        worker.getCuttingTools().put(tool, tempQuantity + quantity);

        cuttingToolsRepo.save(tool);
        workerRepo.save(worker);
    }

    @Override
    public void delete(long id) {
        cuttingToolsRepo.deleteById(id);
    }

}
