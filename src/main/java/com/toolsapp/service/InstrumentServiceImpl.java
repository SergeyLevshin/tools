package com.toolsapp.service;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.instrument.CuttingTool;
import com.toolsapp.repository.CuttingToolsRepository;
import com.toolsapp.repository.WorkerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InstrumentServiceImpl implements InstrumentService {

    private final CuttingToolsRepository cuttingToolsRepo;
    private final WorkerRepository workerRepo;

    public InstrumentServiceImpl(CuttingToolsRepository cuttingToolsRepo, WorkerRepository workerRepo) {
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
        //TODO need to extract methods and handle NPE instead .orElse(...);
        CuttingTool tool = cuttingToolsRepo.findById(toolId).orElse(null);
        Worker worker = workerRepo.findById(workerId).orElse(null);

        if (tool.getQuantity() < quantity)
            return;

        tool.setQuantity(tool.getQuantity() - quantity);

        int tempQuantity = worker.getCuttingTools().getOrDefault(tool.getId(), 0);
        worker.getCuttingTools().put(tool.getId(), tempQuantity + quantity);

        cuttingToolsRepo.save(tool);
        workerRepo.save(worker);
    }

//    private void checkToolQuantity(Long toolId, int quantity) {
//        CuttingTool tool = cuttingToolsRepo.findById(toolId).orElse(new CuttingTool());
//        if (tool.getQuantity() < quantity)
//            return;
//        tool.setQuantity(tool.getQuantity() - quantity);
//    }

    @Transactional
    @Override
    public void delete(long id) {
        //TODO check quantity of selected tool(by id) from all workers
        //TODO if quantity from each worker == 0, then delete, else do nothing
        //TODO or throw some exception
        workerRepo.findAll();
        cuttingToolsRepo.deleteById(id);
    }

}
