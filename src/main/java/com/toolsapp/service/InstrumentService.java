package com.toolsapp.service;

import com.toolsapp.models.extra.Worker;
import com.toolsapp.models.instrument.CuttingTool;

import java.util.List;

public interface InstrumentService {
    
    void save(CuttingTool cuttingTool);

    void giveToolToWorker(CuttingTool tool, int quantity, Worker worker);

    void delete(CuttingTool tool);

    List<CuttingTool> findAll();
}
