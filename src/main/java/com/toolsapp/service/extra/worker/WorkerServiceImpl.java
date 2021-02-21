package com.toolsapp.service.extra.worker;

import com.toolsapp.domain.extra.Worker;
import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.repository.extra.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository repository;

    public WorkerServiceImpl(WorkerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Worker> findAll() {
        return (List<Worker>) repository.findAll();
    }

    @Override
    public Worker findById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public void save(Worker worker) {
        repository.save(worker);
    }

    @Override
    public Map<AbstractTool, Integer> getWorkerTools(long workerId) {
        Optional<Worker> worker = repository.findById(workerId);
        if (worker.isPresent()) {
            return worker.get().getTools();
        }
        else {
            return new HashMap<>();
        }
    }

    @Override
    public void removeToolFromWorker(long workerId, long toolId, int quantity) {
        Worker worker = repository.findById(workerId).orElseThrow(NoSuchFieldError::new);
        Optional<AbstractTool> tool = worker.getTools()
                .keySet()
                .stream()
                .filter(t -> t.getId() == toolId)
                .findFirst();
        tool.ifPresent(t -> worker.decreaseToolQuantity(t, quantity));
        repository.save(worker);
    }

}
