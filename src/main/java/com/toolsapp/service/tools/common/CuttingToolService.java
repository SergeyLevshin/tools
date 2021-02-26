package com.toolsapp.service.tools.common;

import com.toolsapp.domain.tools.CuttingTool;
import com.toolsapp.repository.CommonRepository;
import com.toolsapp.repository.tools.CuttingToolsRepository;
import com.toolsapp.service.AbstractCommonService;
import org.springframework.stereotype.Service;

@Service
public class CuttingToolService
        extends AbstractCommonService<CuttingTool, CommonRepository<CuttingTool>> {
    public CuttingToolService(CuttingToolsRepository repository) {
        super(repository);
    }
}
