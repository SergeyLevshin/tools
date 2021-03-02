package com.toolsapp.service.extra.worker;

import com.toolsapp.domain.extra.Worker;
import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.repository.CommonRepository;
import com.toolsapp.service.AbstractCommonService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkerService extends AbstractCommonService<Worker, CommonRepository<Worker>> {

    protected WorkerService(CommonRepository<Worker> repository) {
        super(repository);
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

    public boolean removeToolFromWorker(long workerId, long toolId, int quantity) {
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
            return true;
        }
        else {
            return false;
        }
    }
}
