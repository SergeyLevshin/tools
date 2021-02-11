package com.toolsapp.service.extra;

import com.toolsapp.models.extra.Worker;

import java.util.List;
import java.util.Optional;

public interface WorkerService {
    List<Worker> findAll();
    Optional<Worker> findById(long id);
}
