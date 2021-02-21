package com.toolsapp.service.tools.common;

import com.toolsapp.domain.tools.SupportTool;
import com.toolsapp.repository.tools.SupportToolRepository;
import org.springframework.stereotype.Service;

@Service
public class SupportToolService
        extends AbstractToolService<SupportTool, SupportToolRepository> {
    public SupportToolService(SupportToolRepository repository) {
        super(repository);
    }
}
