package com.toolsapp.service;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository repository;

    @Autowired
    public WorkerServiceImpl(WorkerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Worker> findAll() {
        return (List<Worker>) repository.findAll();
    }

}
