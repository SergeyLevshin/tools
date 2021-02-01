package com.toolsapp.service;

import com.toolsapp.models.instrument.CuttingTool;

import java.util.List;

public interface InstrumentService {
    
    void save(CuttingTool cuttingTool);

    void giveToolToWorker(long toolId, int quantity, long workerId);

    void delete(long id);

    List<CuttingTool> findAll();
}
