package com.toolsapp.repository;

import com.toolsapp.models.instrument.CuttingTool;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CuttingToolsRepository extends CrudRepository<CuttingTool, Long> {
    List<CuttingTool> findAll();
}
