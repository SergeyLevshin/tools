package com.toolsapp.service.extra.worker;

import com.toolsapp.domain.extra.Worker;
import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.repository.CommonRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final CommonRepository<Worker> repository;

    public WorkerServiceImpl(CommonRepository<Worker> repository) {
        this.repository = repository;
    }

    @Override
    public List<Worker> findAll() {
        return (List<Worker>) repository.findAll();
    }

    @Override
    public Optional<Worker> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Worker save(Worker worker) {
        return repository.save(worker);
    }

    @Override
    public Map<AbstractTool, Integer> getWorkerTools(long workerId) {
        Optional<Worker> worker = repository.findById(workerId);
        if (worker.isPresent()) {
            return extractTools(worker.get());
        }
        else {
            return new HashMap<>();
        }

    }

    private Map<AbstractTool, Integer> extractTools(Worker worker) {
        return worker.getTools();
    }

    @Override
    public void removeToolFromWorker(long workerId, long toolId, int quantity) {
        Optional<Worker> worker = repository.findById(workerId);
        if (worker.isPresent()) {
            Worker returnedWorker = worker.get();
            Optional<AbstractTool> tool = returnedWorker.getTools()
                    .keySet()
                    .stream()
                    .filter(t -> t.getId() == toolId)
                    .findFirst();
            tool.ifPresent(t -> returnedWorker.decreaseToolQuantity(t, quantity));
            repository.save(returnedWorker);
        }
    }
}
