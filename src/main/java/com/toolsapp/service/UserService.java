package com.toolsapp.service;

import com.toolsapp.models.instrument.CuttingTool;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<CuttingTool> findAll();
    Optional<CuttingTool> save(CuttingTool cuttingTool);
}
