package com.toolsapp.models.extra;

import com.toolsapp.models.instrument.Accessory;
import com.toolsapp.models.instrument.CuttingTool;
import com.toolsapp.models.instrument.MeasuringTool;

import java.util.List;

public class Worker {

    private int id;

    public int getId() {
        return id;
    }

    private List<Accessory> accessoryList;

    private List<CuttingTool> cuttingTools;

    private List<MeasuringTool> measuringTools;

}
