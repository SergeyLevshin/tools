package com.toolsapp.repository;

import com.toolsapp.models.instrument.CuttingTool;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuttingToolsRepository extends CrudRepository<CuttingTool, Long> {
}
