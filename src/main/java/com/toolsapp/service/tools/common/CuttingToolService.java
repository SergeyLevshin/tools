package com.toolsapp.service.tools.common;

import com.toolsapp.domain.tools.CuttingTool;
import com.toolsapp.repository.tools.CuttingToolsRepository;
import org.springframework.stereotype.Service;

@Service
public class CuttingToolService
        extends AbstractToolService<CuttingTool, CuttingToolsRepository> {
    public CuttingToolService(CuttingToolsRepository repository) {
        super(repository);
    }
}
