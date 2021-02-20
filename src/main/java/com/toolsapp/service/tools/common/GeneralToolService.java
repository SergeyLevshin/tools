package com.toolsapp.service.tools.common;

import com.toolsapp.models.tools.AbstractTool;
import com.toolsapp.models.tools.CuttingTool;
import com.toolsapp.models.tools.MeasuringTool;
import com.toolsapp.models.tools.SupportTool;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GeneralToolService {
    private final SupportToolService supportToolService;
    private final CuttingToolService cuttingToolService;
    private final MeasuringToolService measuringToolService;

    public GeneralToolService(SupportToolService supportToolService,
                              CuttingToolService cuttingToolService,
                              MeasuringToolService measuringToolService) {
        this.supportToolService = supportToolService;
        this.cuttingToolService = cuttingToolService;
        this.measuringToolService = measuringToolService;
    }

    public SupportToolService getSupportToolService() {
        return supportToolService;
    }

    public CuttingToolService getCuttingToolService() {
        return cuttingToolService;
    }

    public MeasuringToolService getMeasuringToolService() {
        return measuringToolService;
    }

    public List<SupportTool> findAllSupportTools() {
        return supportToolService.findAll();
    }

    public List<CuttingTool> findAllCuttingTools() {
        return cuttingToolService.findAll();
    }

    public List<MeasuringTool> findAllMeasuringTools() {
        return measuringToolService.findAll();
    }

    public List<AbstractTool> findAllTools() {
        List<SupportTool> supportTools = findAllSupportTools();
        List<CuttingTool> cuttingTools = findAllCuttingTools();
        List<MeasuringTool> measuringTools = findAllMeasuringTools();
        return Stream.of(supportTools, cuttingTools, measuringTools)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
