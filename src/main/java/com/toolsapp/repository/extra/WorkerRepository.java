package com.toolsapp.repository.extra;

import com.toolsapp.models.extra.Worker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends CrudRepository<Worker, Long> {
}
