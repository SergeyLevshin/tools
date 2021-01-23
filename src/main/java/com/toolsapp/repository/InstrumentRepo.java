package com.toolsapp.repository;

import com.toolsapp.models.instrument.Accessory;
import com.toolsapp.models.instrument.CuttingTool;
import com.toolsapp.models.instrument.Instrument;
import com.toolsapp.models.instrument.MeasuringTool;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InstrumentRepo {

    private static List<Instrument> toolList = new ArrayList<>();

    static {
        toolList.add(new Accessory(1, "Chuck"));
        toolList.add(new CuttingTool(2, "Tungalue"));
        toolList.add(new MeasuringTool(3, "SHC"));
    }

    public static List<Instrument> getToolList() {
        return toolList;
    }
}
