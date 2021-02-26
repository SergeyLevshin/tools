package com.toolsapp.service.tools.common;

import com.toolsapp.domain.tools.SupportTool;
import com.toolsapp.repository.CommonRepository;
import com.toolsapp.repository.tools.SupportToolRepository;
import com.toolsapp.service.AbstractCommonService;
import org.springframework.stereotype.Service;

@Service
public class SupportToolService
        extends AbstractCommonService<SupportTool, CommonRepository<SupportTool>> {
    public SupportToolService(SupportToolRepository repository) {
        super(repository);
    }
}
