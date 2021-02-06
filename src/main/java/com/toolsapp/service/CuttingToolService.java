package com.toolsapp.service;

import com.toolsapp.models.tools.CuttingTool;

import java.util.List;

public interface CuttingToolService {
    
    void save(CuttingTool cuttingTool);

    void giveToolToWorker(long toolId, int quantity, long workerId);

    void delete(long id);

    List<CuttingTool> findAll();

    CuttingTool findById(long id);

}
