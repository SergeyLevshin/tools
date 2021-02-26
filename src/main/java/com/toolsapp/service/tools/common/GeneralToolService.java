package com.toolsapp.service.tools.common;

import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.domain.tools.CuttingTool;
import com.toolsapp.domain.tools.MeasuringTool;
import com.toolsapp.domain.tools.SupportTool;
import com.toolsapp.repository.CommonRepository;
import com.toolsapp.service.AbstractCommonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//TODO make it simpler

@Service
public class GeneralToolService extends AbstractCommonService<AbstractTool, CommonRepository<AbstractTool>> {

    public GeneralToolService(CommonRepository<AbstractTool> repository) {
        super(repository);
    }

    public List<CuttingTool> findAllCuttingTools() {
        return findAll().stream()
                .filter(t -> t.getClass().equals(CuttingTool.class))
                .map(t -> (CuttingTool) t)
                .collect(Collectors.toList());
    }


    public List<SupportTool> findAllSupportTools() {
        return findAll().stream()
                .filter(t -> t.getClass().equals(SupportTool.class))
                .map(t -> (SupportTool) t)
                .collect(Collectors.toList());
    }

    public List<MeasuringTool> findAllMeasuringTools() {
        return findAll().stream()
                .filter(t -> t.getClass().equals(MeasuringTool.class))
                .map(t -> (MeasuringTool) t)
                .collect(Collectors.toList());
    }
}
