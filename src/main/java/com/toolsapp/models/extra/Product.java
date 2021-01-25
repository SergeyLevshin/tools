package com.toolsapp.models.extra;

import com.toolsapp.models.instrument.Accessory;
import com.toolsapp.models.instrument.CuttingTool;
import com.toolsapp.models.instrument.MeasuringTool;

import java.util.List;

public class Product {

    private long id;

    private String name;

    private List<Accessory> accessoryList;

    private List<CuttingTool> cuttingTools;

    private List<MeasuringTool> measuringTools;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
