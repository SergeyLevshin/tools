package com.toolsapp.service.extra;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.repository.extra.WorkerRepository;
import com.toolsapp.repository.tools.CuttingToolsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;

    public WorkerServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public List<Worker> findAll() {
        return (List<Worker>) workerRepository.findAll();
    }

    @Override
    public Optional<Worker> findById(long id) {
        return Optional.ofNullable(workerRepository.findById(id).orElseThrow(NoSuchElementException::new));
    }

    @Transactional
    public void removeToolFromWorker(long workerId, long toolId, int quantity) {
        Worker worker = workerRepository.findById(workerId).orElseThrow();
        int newQuantity = worker.getCuttingTools().get(toolId) - quantity;
        if (newQuantity > 0)
            worker.getCuttingTools().put(toolId, newQuantity);
        if (newQuantity == 0)
            worker.getCuttingTools().remove(toolId);
        workerRepository.save(worker);
    }

    @Transactional
    public void setToolQuantityFromWorker(long workerId, long toolId, int quantity){
        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(NoSuchElementException::new);
        int tempQuantity = worker.getCuttingTools().getOrDefault(toolId, 0);
        worker.getCuttingTools().put(toolId, tempQuantity + quantity);
        workerRepository.save(worker);
    }
}
