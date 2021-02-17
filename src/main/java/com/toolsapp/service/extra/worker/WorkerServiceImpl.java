package com.toolsapp.service.extra.worker;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.repository.extra.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Optional<Worker> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public void save(Worker worker) {
        repository.save(worker);
    }

}
