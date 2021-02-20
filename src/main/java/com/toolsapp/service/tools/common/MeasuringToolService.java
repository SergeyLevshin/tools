package com.toolsapp.service.tools.common;

import com.toolsapp.models.tools.MeasuringTool;
import com.toolsapp.repository.tools.MeasuringToolRepository;
import org.springframework.stereotype.Service;

@Service
public class MeasuringToolService
        extends AbstractToolService<MeasuringTool, MeasuringToolRepository> {
    public MeasuringToolService(MeasuringToolRepository repository) {
        super(repository);
    }
}
