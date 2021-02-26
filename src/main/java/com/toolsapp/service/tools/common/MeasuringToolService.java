package com.toolsapp.service.tools.common;

import com.toolsapp.domain.tools.MeasuringTool;
import com.toolsapp.repository.CommonRepository;
import com.toolsapp.repository.tools.MeasuringToolRepository;
import com.toolsapp.service.AbstractCommonService;
import org.springframework.stereotype.Service;

@Service
public class MeasuringToolService
        extends AbstractCommonService<MeasuringTool, CommonRepository<MeasuringTool>> {
    public MeasuringToolService(MeasuringToolRepository repository) {
        super(repository);
    }
}
